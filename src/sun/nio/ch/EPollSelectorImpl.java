package sun.nio.ch;

import java.io.IOException;
import java.nio.channels.*;
import java.nio.channels.spi.*;
import java.util.*;
import sun.misc.*;

/**
 * An implementation of Selector for Linux 2.6+ kernels that uses
 * the epoll event notification facility.
 */
class EPollSelectorImpl    extends SelectorImpl
{

    // File descriptors used for interrupt
    // 用于中断的文件描述符
    protected int fd0;
    protected int fd1;

    // EpollArrayWapper将Linux的epoll相关系统调用封装成了native方法供EpollSelectorImpl使用
    // The poll object
    EPollArrayWrapper pollWrapper;

    // 保存socket句柄和selectkey,有注册到selector的channel对应的SelectionKey和与之对应的文件描述符都会放入到该映射表中。
    // Maps from file descriptors to keys
    private Map<Integer,SelectionKeyImpl> fdToKey;

    // True if this Selector has been closed
    private volatile boolean closed = false;

    // Lock for interrupt triggering and clearing
    private final Object interruptLock = new Object();
    private boolean interruptTriggered = false;

    /**
     * Package private constructor called by factory method in
     * the abstract superclass Selector.
     */
    EPollSelectorImpl(SelectorProvider sp) throws IOException {

        /**
         * 首先调用父类SelectorImp的构造方法。JDK中对于注册到Selector上的IO事件关系是使用SelectionKey来表示，
         * 代表了Channel感兴趣的事件，如Read,Write,Connect,Accept。内部初始化publicKeys和publicSelectedKeys，
         * 用到的容器是HashSet，前者用来保存所有的感兴趣的事件，后者准备好的事件
         */
        super(sp);
        //makePipe返回管道的2个文件描述符，编码在一个long类型的变量中。高32位代表读 低32位代表写。
        // 当要中断select方法时，往fd1中写入数字1会导致fd0有可读内容，select方法会返回，使用pipe为了实现Selector的wakeup逻辑
        long pipeFds = IOUtil.makePipe(false);
        fd0 = (int) (pipeFds >>> 32);
        fd1 = (int) pipeFds;
        try {
            pollWrapper = new EPollArrayWrapper();

            pollWrapper.initInterrupt(fd0, fd1);
            //定义了一个map类型的变量fdToKey,将channel的文件描述符ID和SelectionKey建立映射关系,
            // SelectionKey中保存了Channel Selector 感兴趣的事件
            fdToKey = new HashMap<>();
        } catch (Throwable t) {
            try {
                FileDispatcherImpl.closeIntFD(fd0);
            } catch (IOException ioe0) {
                t.addSuppressed(ioe0);
            }
            try {
                FileDispatcherImpl.closeIntFD(fd1);
            } catch (IOException ioe1) {
                t.addSuppressed(ioe1);
            }
            throw t;
        }
    }

    /**
     * 该方法会一直阻塞直到至少一个channel被选择(即，该channel注册的事件发生了为止，除非当前线程发生中断或者selector的wakeup方法被调用
     * 著名的select方法
     * @param timeout
     * @return
     * @throws IOException
     */
    @Override
    protected int doSelect(long timeout) throws IOException {
        if (closed) {
            throw new ClosedSelectorException();
        }
        //处理待取消的SelectionKey(调用SelectionKey.cancel()方法取消)
        processDeregisterQueue();
        try {
            //begin和end方法主要是为了处理线程中断，将线程的中断转化为Selector的wakeup方法，避免线程堵塞在IO操作上
            begin();
            //调用EPollArrayWrapper的方法epoll获取已经就绪的pollfd
            pollWrapper.poll(timeout);
        } finally {
            end();
        }
        processDeregisterQueue();
        //通过fdToKey查找文件描述符对应的SelectionKey，并更新之
        int numKeysUpdated = updateSelectedKeys();
        if (pollWrapper.interrupted()) {
            // Clear the wakeup pipe
            pollWrapper.putEventOps(pollWrapper.interruptedIndex(), 0);
            synchronized (interruptLock) {
                pollWrapper.clearInterrupted();
                IOUtil.drain(fd0);
                interruptTriggered = false;
            }
        }
        return numKeysUpdated;
    }

    /**
     * Update the keys whose fd's have been selected by the epoll.
     * Add the ready keys to the ready queue.
     */
    private int updateSelectedKeys() {
        int entries = pollWrapper.updated;
        int numKeysUpdated = 0;
        for (int i=0; i<entries; i++) {
            int nextFD = pollWrapper.getDescriptor(i);
            SelectionKeyImpl ski = fdToKey.get(Integer.valueOf(nextFD));
            // ski is null in the case of an interrupt
            if (ski != null) {
                int rOps = pollWrapper.getEventOps(i);
                if (selectedKeys.contains(ski)) {
                    if (ski.channel.translateAndSetReadyOps(rOps, ski)) {
                        numKeysUpdated++;
                    }
                } else {
                    ski.channel.translateAndSetReadyOps(rOps, ski);
                    if ((ski.nioReadyOps() & ski.nioInterestOps()) != 0) {
                        selectedKeys.add(ski);
                        numKeysUpdated++;
                    }
                }
            }
        }
        return numKeysUpdated;
    }

    protected void implClose() throws IOException {
        if (closed)
            return;
        closed = true;

        // prevent further wakeup
        synchronized (interruptLock) {
            interruptTriggered = true;
        }

        FileDispatcherImpl.closeIntFD(fd0);
        FileDispatcherImpl.closeIntFD(fd1);

        pollWrapper.closeEPollFD();
        // it is possible
        selectedKeys = null;

        // Deregister channels
        Iterator<SelectionKey> i = keys.iterator();
        while (i.hasNext()) {
            SelectionKeyImpl ski = (SelectionKeyImpl)i.next();
            deregister(ski);
            SelectableChannel selch = ski.channel();
            if (!selch.isOpen() && !selch.isRegistered())
                ((SelChImpl)selch).kill();
            i.remove();
        }

        fd0 = -1;
        fd1 = -1;
    }

    protected void implRegister(SelectionKeyImpl ski) {
        if (closed)
            throw new ClosedSelectorException();

        //将channel对应的fd(文件描述符)和对应的SelectionKey放到fdToKey映射表中。fdToKey是一个map类型的结构，用来保存fd和key的映射关系
        SelChImpl ch = ski.channel;
        int fd = Integer.valueOf(ch.getFDVal());
        fdToKey.put(fd, ski);

        //将channel对应的fd(文件描述符)添加到EPollArrayWrapper中，并强制初始化fd的事件为0
        // ( 强制初始更新事件为0，因为该事件可能存在于之前被取消过的注册中。)
        pollWrapper.add(fd);
        //将selectionKey放入到keys集合中
        keys.add(ski);
    }

    protected void implDereg(SelectionKeyImpl ski) throws IOException {
        assert (ski.getIndex() >= 0);
        SelChImpl ch = ski.channel;
        int fd = ch.getFDVal();
        fdToKey.remove(Integer.valueOf(fd));
        pollWrapper.remove(fd);
        ski.setIndex(-1);
        keys.remove(ski);
        selectedKeys.remove(ski);
        deregister((AbstractSelectionKey)ski);
        SelectableChannel selch = ski.channel();
        if (!selch.isOpen() && !selch.isRegistered())
            ((SelChImpl)selch).kill();
    }

    public void putEventOps(SelectionKeyImpl ski, int ops) {
        if (closed)
            throw new ClosedSelectorException();
        SelChImpl ch = ski.channel;
        pollWrapper.setInterest(ch.getFDVal(), ops);
    }

    public Selector wakeup() {
        synchronized (interruptLock) {
            if (!interruptTriggered) {
                pollWrapper.interrupt();
                interruptTriggered = true;
            }
        }
        return this;
    }
}
