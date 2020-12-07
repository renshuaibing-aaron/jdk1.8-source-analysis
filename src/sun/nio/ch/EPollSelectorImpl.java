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
    // �����жϵ��ļ�������
    protected int fd0;
    protected int fd1;

    // EpollArrayWapper��Linux��epoll���ϵͳ���÷�װ����native������EpollSelectorImplʹ��
    // The poll object
    EPollArrayWrapper pollWrapper;

    // ����socket�����selectkey,��ע�ᵽselector��channel��Ӧ��SelectionKey����֮��Ӧ���ļ�������������뵽��ӳ����С�
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
         * ���ȵ��ø���SelectorImp�Ĺ��췽����JDK�ж���ע�ᵽSelector�ϵ�IO�¼���ϵ��ʹ��SelectionKey����ʾ��
         * ������Channel����Ȥ���¼�����Read,Write,Connect,Accept���ڲ���ʼ��publicKeys��publicSelectedKeys��
         * �õ���������HashSet��ǰ�������������еĸ���Ȥ���¼�������׼���õ��¼�
         */
        super(sp);
        //makePipe���عܵ���2���ļ���������������һ��long���͵ı����С���32λ����� ��32λ����д��
        // ��Ҫ�ж�select����ʱ����fd1��д������1�ᵼ��fd0�пɶ����ݣ�select�����᷵�أ�ʹ��pipeΪ��ʵ��Selector��wakeup�߼�
        long pipeFds = IOUtil.makePipe(false);
        fd0 = (int) (pipeFds >>> 32);
        fd1 = (int) pipeFds;
        try {
            pollWrapper = new EPollArrayWrapper();

            pollWrapper.initInterrupt(fd0, fd1);
            //������һ��map���͵ı���fdToKey,��channel���ļ�������ID��SelectionKey����ӳ���ϵ,
            // SelectionKey�б�����Channel Selector ����Ȥ���¼�
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
     * �÷�����һֱ����ֱ������һ��channel��ѡ��(������channelע����¼�������Ϊֹ�����ǵ�ǰ�̷߳����жϻ���selector��wakeup����������
     * ������select����
     * @param timeout
     * @return
     * @throws IOException
     */
    @Override
    protected int doSelect(long timeout) throws IOException {
        if (closed) {
            throw new ClosedSelectorException();
        }
        //�����ȡ����SelectionKey(����SelectionKey.cancel()����ȡ��)
        processDeregisterQueue();
        try {
            //begin��end������Ҫ��Ϊ�˴����߳��жϣ����̵߳��ж�ת��ΪSelector��wakeup�����������̶߳�����IO������
            begin();
            //����EPollArrayWrapper�ķ���epoll��ȡ�Ѿ�������pollfd
            pollWrapper.poll(timeout);
        } finally {
            end();
        }
        processDeregisterQueue();
        //ͨ��fdToKey�����ļ���������Ӧ��SelectionKey��������֮
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

        //��channel��Ӧ��fd(�ļ�������)�Ͷ�Ӧ��SelectionKey�ŵ�fdToKeyӳ����С�fdToKey��һ��map���͵Ľṹ����������fd��key��ӳ���ϵ
        SelChImpl ch = ski.channel;
        int fd = Integer.valueOf(ch.getFDVal());
        fdToKey.put(fd, ski);

        //��channel��Ӧ��fd(�ļ�������)��ӵ�EPollArrayWrapper�У���ǿ�Ƴ�ʼ��fd���¼�Ϊ0
        // ( ǿ�Ƴ�ʼ�����¼�Ϊ0����Ϊ���¼����ܴ�����֮ǰ��ȡ������ע���С�)
        pollWrapper.add(fd);
        //��selectionKey���뵽keys������
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
