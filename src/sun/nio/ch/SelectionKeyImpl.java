package sun.nio.ch;

import java.io.IOException;
import java.nio.channels.*;
import java.nio.channels.spi.*;


/**
 * An implementation of SelectionKey for Solaris.
 */

public class SelectionKeyImpl   extends AbstractSelectionKey
{

    final SelChImpl channel;                            // package-private
    public final SelectorImpl selector;

    // Index for a pollfd array in Selector that this key is registered with
    private int index;

    //通过interestOps来确定了selector在下一个选择操作的过程中将测试哪些操作类别的准备情况，操作事件是否是channel关注的
    private volatile int interestOps;

    //readyOps表示通过Selector检测到channel已经准备就绪的操作事件。在SelectionKey创建时（即上面源码所示），
    // readyOps值为0，在Selector的select操作中可能会更新，但是需要注意的是我们不能直接调用来更新
    private int readyOps;

    SelectionKeyImpl(SelChImpl ch, SelectorImpl sel) {
        channel = ch;
        selector = sel;
    }

    public SelectableChannel channel() {
        return (SelectableChannel)channel;
    }

    public Selector selector() {
        return selector;
    }

    int getIndex() {                                    // package-private
        return index;
    }

    void setIndex(int i) {                              // package-private
        index = i;
    }

    private void ensureValid() {
        if (!isValid())
            throw new CancelledKeyException();
    }

    public int interestOps() {
        ensureValid();
        return interestOps;
    }

    public SelectionKey interestOps(int ops) {
        ensureValid();
        return nioInterestOps(ops);
    }

    public int readyOps() {
        ensureValid();
        return readyOps;
    }

    // The nio versions of these operations do not care if a key
    // has been invalidated. They are for internal use by nio code.

    public void nioReadyOps(int ops) {
        readyOps = ops;
    }

    public int nioReadyOps() {
        return readyOps;
    }

    public SelectionKey nioInterestOps(int ops) {
        if ((ops & ~channel().validOps()) != 0)
            throw new IllegalArgumentException();
        //translateAndSetInterestOps方法会将注册的感兴趣的事件和其对应的文件描述存储到EPollArrayWrapper对象的eventsLow或eventsHigh中，
        // 这是给底层实现epoll_wait时使用的。同时该操作还会将设置SelectionKey的interestOps字段，这是给我们程序员获取使用的
        channel.translateAndSetInterestOps(ops, this);
        interestOps = ops;
        return this;
    }

    public int nioInterestOps() {
        return interestOps;
    }

}
