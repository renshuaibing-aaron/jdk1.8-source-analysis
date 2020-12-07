package java.nio.channels;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.io.IOException;


/**
 * todo SelectionKey表示一个可选择通道与选择器关联的注册器，可以简单理解为一个token。
 * A token representing the registration of a {@link SelectableChannel} with a
 * {@link Selector}.
 *
 * todo SelectionKey在每次通道注册到选择器时创建。SelectionKey在其调用取消方法，或自己的通道
 *  关闭，或关联的选择器关闭之前，都是有效。取消一个SelectionKey，不会立刻从选择器移除；
 *  而是在下一次通选器选择操作的过程，取消的SelectionKey从SelectionKey集合移除是无效的。
 *  我们可以通过#isValid判断一个SelectionKey是否有效。
 *
 * <p> A selection key is created each time a channel is registered with a
 * selector.  A key remains valid until it is <i>cancelled</i> by invoking its
 * {@link #cancel cancel} method, by closing its channel, or by closing its
 * selector.  Cancelling a key does not immediately remove it from its
 * selector; it is instead added to the selector's <a
 * href="Selector.html#ks"><i>cancelled-key set</i></a> for removal during the
 * next selection operation.  The validity of a key may be tested by invoking
 * its {@link #isValid isValid} method.
 *
 * <a name="opsets"></a>
 *
 * todo  SelectionKey包含两个操作集，每个操作集用一个Integer来表示，int值中的低四位的bit
 *  用于表示通道支持的可选操作种类。
 *
 * <p> A selection key contains two <i>operation sets</i> represented as
 * integer values.  Each bit of an operation set denotes a category of
 * selectable operations that are supported by the key's channel.
 *
 * <ul>
 *
 * todo interest集合决定了选择器在下一个选择操作的过程中，操作事件是否是通道关注的。兴趣操作事件集
 *  在SelectionKey创建时，初始化为注册选择器时的opt值，这个值可能通过interestOps(int)会改变。
 *
 *   <li><p> The <i>interest set</i> determines which operation categories will
 *   be tested for readiness the next time one of the selector's selection
 *   methods is invoked.  The interest set is initialized with the value given
 *   when the key is created; it may later be changed via the {@link
 *   #interestOps(int)} method. </p></li>
 *
 *  todo ready集合表示通过选择器探测到通道已经准备就绪的操作事件。在SelectionKey创建时时，
 *     就绪操作事件集值为0，在选择器的选择操作中可能会更新，但是不能直接的更新。
 *
 *   <li><p> The <i>ready set</i> identifies the operation categories for which
 *   the key's channel has been detected to be ready by the key's selector.
 *   The ready set is initialized to zero when the key is created; it may later
 *   be updated by the selector during a selection operation, but it cannot be
 *   updated directly. </p></li>
 *
 * </ul>
 *
 * <p> That a selection key's ready set indicates that its channel is ready for
 * some operation category is a hint, but not a guarantee, that an operation in
 * such a category may be performed by a thread without causing the thread to
 * block.  A ready set is most likely to be accurate immediately after the
 * completion of a selection operation.  It is likely to be made inaccurate by
 * external events and by I/O operations that are invoked upon the
 * corresponding channel.
 *
 * todo SelectionKey的ready集合表示一个通道已经准备就绪的操作事件，但不能保证在没有引起线程
 *   阻塞的情况下，就绪的操作事件会被线程执行。在一个选择操作完成后，就绪操作事件集，
 *    大部分情况下回立即，更新。如果外部的事件或在通道有IO操作，就绪操作事件集可能不准确。
 *
 *
 * <p> This class defines all known operation-set bits, but precisely which
 * bits are supported by a given channel depends upon the type of the channel.
 * Each subclass of {@link SelectableChannel} defines an {@link
 * SelectableChannel#validOps() validOps()} method which returns a set
 * identifying just those operations that are supported by the channel.  An
 * attempt to set or test an operation-set bit that is not supported by a key's
 * channel will result in an appropriate run-time exception.
 *
 *  todo SelectionKey定义了所有的操作事件，但是具体通道支持的操作事件依赖于具体的通道。
 *   所有可选择的通道都可以通过validOps方法，判断一个操作事件是否被通道所支持。测试一个
 *   不被通道所支持的通道，将会抛出相关的运行时异常。
 *
 *
 * <p> It is often necessary to associate some application-specific data with a
 * selection key, for example an object that represents the state of a
 * higher-level protocol and handles readiness notifications in order to
 * implement that protocol.  Selection keys therefore support the
 * <i>attachment</i> of a single arbitrary object to a key.  An object can be
 * attached via the {@link #attach attach} method and then later retrieved via
 * the {@link #attachment() attachment} method.
 *
 * todo  如果需要经常关联一些应用的特殊数据到SelectionKey，比如一个object表示一个高层协议的
 *   状态，object用于通知实现协议处理器。所以，SelectionKey支持通过attach方法将一个对象
 *    附加的SelectionKey的attachment上。attachment可以通过#attachment方法进行修改。
 *
 * <p> Selection keys are safe for use by multiple concurrent threads.  The
 * operations of reading and writing the interest set will, in general, be
 * synchronized with certain operations of the selector.  Exactly how this
 * synchronization is performed is implementation-dependent: In a naive
 * implementation, reading or writing the interest set may block indefinitely
 * if a selection operation is already in progress; in a high-performance
 * implementation, reading or writing the interest set may block briefly, if at
 * all.  In any case, a selection operation will always use the interest-set
 * value that was current at the moment that the operation began.  </p>
 *
 *   todo SelectionKey多线程并发访问时，是线程安全的。读写兴趣操作事件集的操作都将同步到，
 *      选择器的具体操作。同步器执行过程是依赖实现的：在一个本地实现版本中，如果一个选择操作正在
 *     进行，读写兴趣操作事件集也许会不确定地阻塞；在一个高性能的实现版本中，可能会简单阻塞。
 *      无论任何时候，一个选择操作在操作开始时，选择器总是占用着兴趣操作事件集的值。
 *
 * @author Mark Reinhold
 * @author JSR-51 Expert Group
 * @since 1.4
 *
 * @see SelectableChannel
 * @see Selector
 */

public abstract class SelectionKey {

    /**
     * Constructs an instance of this class.
     */
    protected SelectionKey() { }


    // -- Channel and selector operations --

    /**
     * Returns the channel for which this key was created.  This method will
     * continue to return the channel even after the key is cancelled.
     *
     * @return  This key's channel
     */
    public abstract SelectableChannel channel();

    /**
     * Returns the selector for which this key was created.  This method will
     * continue to return the selector even after the key is cancelled.
     *
     * @return  This key's selector
     */
    public abstract Selector selector();

    /**
     * Tells whether or not this key is valid.
     *
     * <p> A key is valid upon creation and remains so until it is cancelled,
     * its channel is closed, or its selector is closed.  </p>
     *
     * @return  <tt>true</tt> if, and only if, this key is valid
     */
    public abstract boolean isValid();

    /**
     * Requests that the registration of this key's channel with its selector
     * be cancelled.  Upon return the key will be invalid and will have been
     * added to its selector's cancelled-key set.  The key will be removed from
     * all of the selector's key sets during the next selection operation.
     *
     * <p> If this key has already been cancelled then invoking this method has
     * no effect.  Once cancelled, a key remains forever invalid. </p>
     *
     * <p> This method may be invoked at any time.  It synchronizes on the
     * selector's cancelled-key set, and therefore may block briefly if invoked
     * concurrently with a cancellation or selection operation involving the
     * same selector.  </p>
     */
    public abstract void cancel();


    // -- Operation-set accessors --

    /**
     * 通过interestOps来确定了selector在下一个选择操作的过程中将测试哪些操作类别的准备情况，操作事件是否是channel关注的。
     * interestOps 在SelectionKey创建时，初始化为注册Selector时的ops值，这个值可通过sun.nio.ch.SelectionKeyImpl#interestOps(int)来改变
     * Retrieves this key's interest set.
     *
     * <p> It is guaranteed that the returned set will only contain operation
     * bits that are valid for this key's channel.
     *
     * <p> This method may be invoked at any time.  Whether or not it blocks,
     * and for how long, is implementation-dependent.  </p>
     *
     * @return  This key's interest set
     *
     * @throws  CancelledKeyException
     *          If this key has been cancelled
     */
    public abstract int interestOps();

    /**
     * 通过interestOps来确定了selector在下一个选择操作的过程中将测试哪些操作类别的准备情况，操作事件是否是channel关注的
     * Sets this key's interest set to the given value.
     *
     * <p> This method may be invoked at any time.  Whether or not it blocks,
     * and for how long, is implementation-dependent.  </p>
     *
     * @param  ops  The new interest set
     *
     * @return  This selection key
     *
     * @throws  IllegalArgumentException
     *          If a bit in the set does not correspond to an operation that
     *          is supported by this key's channel, that is, if
     *          {@code (ops & ~channel().validOps()) != 0}
     *
     * @throws  CancelledKeyException
     *          If this key has been cancelled
     */
    public abstract SelectionKey interestOps(int ops);

    /**
     * Retrieves this key's ready-operation set.
     *
     * <p> It is guaranteed that the returned set will only contain operation
     * bits that are valid for this key's channel.  </p>
     *
     * @return  This key's ready-operation set
     *
     * @throws  CancelledKeyException
     *          If this key has been cancelled
     */
    public abstract int readyOps();


    // -- Operation bits and bit-testing convenience methods --

    /**
     * Operation-set bit for read operations.
     *
     * <p> Suppose that a selection key's interest set contains
     * <tt>OP_READ</tt> at the start of a <a
     * href="Selector.html#selop">selection operation</a>.  If the selector
     * detects that the corresponding channel is ready for reading, has reached
     * end-of-stream, has been remotely shut down for further reading, or has
     * an error pending, then it will add <tt>OP_READ</tt> to the key's
     * ready-operation set and add the key to its selected-key&nbsp;set.  </p>
     */
    public static final int OP_READ = 1 << 0;

    /**
     * Operation-set bit for write operations.
     *
     * <p> Suppose that a selection key's interest set contains
     * <tt>OP_WRITE</tt> at the start of a <a
     * href="Selector.html#selop">selection operation</a>.  If the selector
     * detects that the corresponding channel is ready for writing, has been
     * remotely shut down for further writing, or has an error pending, then it
     * will add <tt>OP_WRITE</tt> to the key's ready set and add the key to its
     * selected-key&nbsp;set.  </p>
     */
    public static final int OP_WRITE = 1 << 2;

    /**
     * Operation-set bit for socket-connect operations.
     *
     * <p> Suppose that a selection key's interest set contains
     * <tt>OP_CONNECT</tt> at the start of a <a
     * href="Selector.html#selop">selection operation</a>.  If the selector
     * detects that the corresponding socket channel is ready to complete its
     * connection sequence, or has an error pending, then it will add
     * <tt>OP_CONNECT</tt> to the key's ready set and add the key to its
     * selected-key&nbsp;set.  </p>
     */
    public static final int OP_CONNECT = 1 << 3;

    /**
     * Operation-set bit for socket-accept operations.
     *
     * <p> Suppose that a selection key's interest set contains
     * <tt>OP_ACCEPT</tt> at the start of a <a
     * href="Selector.html#selop">selection operation</a>.  If the selector
     * detects that the corresponding server-socket channel is ready to accept
     * another connection, or has an error pending, then it will add
     * <tt>OP_ACCEPT</tt> to the key's ready set and add the key to its
     * selected-key&nbsp;set.  </p>
     */
    public static final int OP_ACCEPT = 1 << 4;

    /**
     * Tests whether this key's channel is ready for reading.
     *
     * <p> An invocation of this method of the form <tt>k.isReadable()</tt>
     * behaves in exactly the same way as the expression
     *
     * <blockquote><pre>{@code
     * k.readyOps() & OP_READ != 0
     * }</pre></blockquote>
     *
     * <p> If this key's channel does not support read operations then this
     * method always returns <tt>false</tt>.  </p>
     *
     * @return  <tt>true</tt> if, and only if,
                {@code readyOps() & OP_READ} is nonzero
     *
     * @throws  CancelledKeyException
     *          If this key has been cancelled
     */
    public final boolean isReadable() {
        return (readyOps() & OP_READ) != 0;
    }

    /**
     * Tests whether this key's channel is ready for writing.
     *
     * <p> An invocation of this method of the form <tt>k.isWritable()</tt>
     * behaves in exactly the same way as the expression
     *
     * <blockquote><pre>{@code
     * k.readyOps() & OP_WRITE != 0
     * }</pre></blockquote>
     *
     * <p> If this key's channel does not support write operations then this
     * method always returns <tt>false</tt>.  </p>
     *
     * @return  <tt>true</tt> if, and only if,
     *          {@code readyOps() & OP_WRITE} is nonzero
     *
     * @throws  CancelledKeyException
     *          If this key has been cancelled
     */
    public final boolean isWritable() {
        return (readyOps() & OP_WRITE) != 0;
    }

    /**
     * Tests whether this key's channel has either finished, or failed to
     * finish, its socket-connection operation.
     *
     * <p> An invocation of this method of the form <tt>k.isConnectable()</tt>
     * behaves in exactly the same way as the expression
     *
     * <blockquote><pre>{@code
     * k.readyOps() & OP_CONNECT != 0
     * }</pre></blockquote>
     *
     * <p> If this key's channel does not support socket-connect operations
     * then this method always returns <tt>false</tt>.  </p>
     *
     * @return  <tt>true</tt> if, and only if,
     *          {@code readyOps() & OP_CONNECT} is nonzero
     *
     * @throws  CancelledKeyException
     *          If this key has been cancelled
     */
    public final boolean isConnectable() {
        return (readyOps() & OP_CONNECT) != 0;
    }

    /**
     * Tests whether this key's channel is ready to accept a new socket
     * connection.
     *
     * <p> An invocation of this method of the form <tt>k.isAcceptable()</tt>
     * behaves in exactly the same way as the expression
     *
     * <blockquote><pre>{@code
     * k.readyOps() & OP_ACCEPT != 0
     * }</pre></blockquote>
     *
     * <p> If this key's channel does not support socket-accept operations then
     * this method always returns <tt>false</tt>.  </p>
     *
     * @return  <tt>true</tt> if, and only if,
     *          {@code readyOps() & OP_ACCEPT} is nonzero
     *
     * @throws  CancelledKeyException
     *          If this key has been cancelled
     */
    public final boolean isAcceptable() {
        return (readyOps() & OP_ACCEPT) != 0;
    }


    // -- Attachments --

    private volatile Object attachment = null;

    private static final AtomicReferenceFieldUpdater<SelectionKey,Object>
        attachmentUpdater = AtomicReferenceFieldUpdater.newUpdater(
            SelectionKey.class, Object.class, "attachment"
        );

    /**
     * Attaches the given object to this key.
     *
     * <p> An attached object may later be retrieved via the {@link #attachment()
     * attachment} method.  Only one object may be attached at a time; invoking
     * this method causes any previous attachment to be discarded.  The current
     * attachment may be discarded by attaching <tt>null</tt>.  </p>
     *
     * @param  ob
     *         The object to be attached; may be <tt>null</tt>
     *
     * @return  The previously-attached object, if any,
     *          otherwise <tt>null</tt>
     */
    public final Object attach(Object ob) {
        return attachmentUpdater.getAndSet(this, ob);
    }

    /**
     * Retrieves the current attachment.
     *
     * @return  The object currently attached to this key,
     *          or <tt>null</tt> if there is no attachment
     */
    public final Object attachment() {
        return attachment;
    }

}
