package sun.nio.ch;

import java.io.IOException;
import java.nio.channels.*;
import java.nio.channels.spi.*;
import java.net.SocketException;
import java.util.*;

/**
 * Base Selector implementation class.
 */

public abstract class SelectorImpl   extends AbstractSelector
{

    // The set of keys with data ready for an operation
    //给Selector内部使用
    //selectedKeys=publicSelectedKeys 表示准备好的事件
    protected Set<SelectionKey> selectedKeys;
    // The set of keys registered with this Selector
    //给Selector内部使用
    //keys=publicKeys 表示所有注册的事件
    protected HashSet<SelectionKey> keys;


    // Public views of the key sets
    //是给程序员使用
    private Set<SelectionKey> publicKeys;             // Immutable
    //是给程序员使用  用法如下
    // Iterator<SelectionKey> items = selector.selectedKeys().iterator();
    private Set<SelectionKey> publicSelectedKeys;     // Removal allowed, but not addition

    protected SelectorImpl(SelectorProvider sp) {
        super(sp);
        keys = new HashSet<SelectionKey>();
        selectedKeys = new HashSet<SelectionKey>();
        if (Util.atBugLevel("1.4")) {
            publicKeys = keys;
            publicSelectedKeys = selectedKeys;
        } else {
            publicKeys = Collections.unmodifiableSet(keys);
            publicSelectedKeys = Util.ungrowableSet(selectedKeys);
        }
    }

    public Set<SelectionKey> keys() {
        if (!isOpen() && !Util.atBugLevel("1.4"))
            throw new ClosedSelectorException();
        return publicKeys;
    }

    public Set<SelectionKey> selectedKeys() {
        if (!isOpen() && !Util.atBugLevel("1.4"))
            throw new ClosedSelectorException();
        return publicSelectedKeys;
    }

    protected abstract int doSelect(long timeout) throws IOException;


   // WindowsSelectorImpl#doSelect
/*    protected int doSelect(long var1) throws IOException {
        if (this.channelArray == null) {
            throw new ClosedSelectorException();
        } else {
            this.timeout = var1;
            this.processDeregisterQueue();
            if (this.interruptTriggered) {
                this.resetWakeupSocket();
                return 0;
            } else {
                this.adjustThreadsCount();
                this.finishLock.reset();
                this.startLock.startThreads();

                try {
                    this.begin();

                    try {
                        this.subSelector.poll();
                    } catch (IOException var7) {
                        this.finishLock.setException(var7);
                    }

                    if (this.threads.size() > 0) {
                        this.finishLock.waitForHelperThreads();
                    }
                } finally {
                    this.end();
                }

                this.finishLock.checkForException();
                this.processDeregisterQueue();
                int var3 = this.updateSelectedKeys();
                this.resetWakeupSocket();
                return var3;
            }
        }
    }*/



    /**
     *
     *
     * @param timeout
     * @return
     * @throws IOException
     */
    private int lockAndDoSelect(long timeout) throws IOException {
        synchronized (this) {
            if (!isOpen())
                throw new ClosedSelectorException();
            synchronized (publicKeys) {
                synchronized (publicSelectedKeys) {
                    //由具体的操作系统来实现
                    //WindowsSelectorImpl#doSelect
                    return doSelect(timeout);
                }
            }
        }
    }

    public int select(long timeout)
        throws IOException
    {
        if (timeout < 0)
            throw new IllegalArgumentException("Negative timeout");
        return lockAndDoSelect((timeout == 0) ? -1 : timeout);
    }

    public int select() throws IOException {
        return select(0);
    }

    public int selectNow() throws IOException {
        return lockAndDoSelect(0);
    }

    public void implCloseSelector() throws IOException {
        wakeup();
        synchronized (this) {
            synchronized (publicKeys) {
                synchronized (publicSelectedKeys) {
                    implClose();
                }
            }
        }
    }

    protected abstract void implClose() throws IOException;

    public void putEventOps(SelectionKeyImpl sk, int ops) { }

    //注册
    @Override
    protected final SelectionKey register(AbstractSelectableChannel ch,
                                          int ops,
                                          Object attachment)
    {
        if (!(ch instanceof SelChImpl)) {
            throw new IllegalSelectorException();
        }
        //以当前channel和selector为参数，初始化SelectionKeyImpl 对象selectionKeyImpl ，并添加附件attachment
        SelectionKeyImpl k = new SelectionKeyImpl((SelChImpl)ch, this);
        k.attach(attachment);

        synchronized (publicKeys) {

            //implRegister(k)在EPollSelectorImpl类中，作用是将channel注册到epoll中
            implRegister(k);
        }
        /**
         * a) 会将注册的感兴趣的事件和其对应的文件描述存储到EPollArrayWrapper对象的eventsLow或eventsHigh中，这是给底层实现epoll_wait时使用的。
         * b) 同时该操作还会将设置SelectionKey的interestOps字段
         */
        //k.interestOps(int) 的内部调用了nioInterestOps方法
        k.interestOps(ops);
        return k;
    }

    protected abstract void implRegister(SelectionKeyImpl ski);

    /**
     * 调用processDeregisterQueue方法，将cancel的selectionKey从selector中删除，底层会调用epoll_ctl方法移除被epoll所监听的文件描述符
     * @throws IOException
     */
    void processDeregisterQueue() throws IOException {
        // Precondition: Synchronized on this, keys, and selectedKeys
        Set<SelectionKey> cks = cancelledKeys();
        synchronized (cks) {
            if (!cks.isEmpty()) {
                Iterator<SelectionKey> i = cks.iterator();
                while (i.hasNext()) {
                    SelectionKeyImpl ski = (SelectionKeyImpl)i.next();
                    try {
                        implDereg(ski);
                    } catch (SocketException se) {
                        throw new IOException("Error deregistering key", se);
                    } finally {
                        i.remove();
                    }
                }
            }
        }
    }

    protected abstract void implDereg(SelectionKeyImpl ski) throws IOException;

    abstract public Selector wakeup();

}
