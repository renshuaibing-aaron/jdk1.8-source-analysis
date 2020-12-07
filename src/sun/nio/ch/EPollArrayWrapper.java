package sun.nio.ch;

import java.io.IOException;
import java.security.AccessController;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import sun.security.action.GetIntegerAction;

/**
 *
 * todo EpollArrayWapper将Linux的epoll相关系统调用封装成了native方法供EpollSelectorImpl使用
 *  EPollArrayWrapper完成了对epoll文件描述符的构建，以及对linux系统的epoll指令操纵的封装。维护每次selection操作的结果，即epoll_wait结果的epoll_event数组。
 *  EPollArrayWrapper操纵了一个linux系统下epoll_event结构的本地数组
 * Manipulates a native array of epoll_event structs on Linux:
 *
 * typedef union epoll_data {
 *     void *ptr;
 *     int fd;
 *     __uint32_t u32;
 *     __uint64_t u64;
 *  } epoll_data_t;
 *
 * struct epoll_event {
 *     __uint32_t events;
 *     epoll_data_t data;
 * };
 *
 * The system call to wait for I/O events is epoll_wait(2). It populates an
 * array of epoll_event structures that are passed to the call. The data
 * member of the epoll_event structure contains the same data as was set
 * when the file descriptor was registered to epoll via epoll_ctl(2). In
 * this implementation we set data.fd to be the file descriptor that we
 * register. That way, we have the file descriptor available when we
 * process the events.
 */

class EPollArrayWrapper {
    // EPOLL_EVENTS
    private static final int EPOLLIN      = 0x001;

    // opcodes
    private static final int EPOLL_CTL_ADD      = 1;
    private static final int EPOLL_CTL_DEL      = 2;
    private static final int EPOLL_CTL_MOD      = 3;

    // Miscellaneous constants
    private static final int SIZE_EPOLLEVENT  = sizeofEPollEvent();
    private static final int EVENT_OFFSET     = 0;
    private static final int DATA_OFFSET      = offsetofData();
    private static final int FD_OFFSET        = DATA_OFFSET;
    private static final int OPEN_MAX         = IOUtil.fdLimit();
    private static final int NUM_EPOLLEVENTS  = Math.min(OPEN_MAX, 8192);

    // Special value to indicate that an update should be ignored
    private static final byte  KILLED = (byte)-1;

    // Initial size of arrays for fd registration changes
    private static final int INITIAL_PENDING_UPDATE_SIZE = 64;

    // maximum size of updatesLow
    private static final int MAX_UPDATE_ARRAY_SIZE = AccessController.doPrivileged(
        new GetIntegerAction("sun.nio.ch.maxUpdateArraySize", Math.min(OPEN_MAX, 64*1024)));

    // The fd of the epoll driver
    private final int epfd;

     // The epoll_event array for results from epoll_wait
    private final AllocatedNativeObject pollArray;

    // Base address of the epoll_event array
    private final long pollArrayAddress;

    // The fd of the interrupt line going out
    private int outgoingInterruptFD;

    // The fd of the interrupt line coming in
    private int incomingInterruptFD;

    // The index of the interrupt FD
    private int interruptedIndex;

    // Number of updated pollfd entries
    int updated;

    // object to synchronize fd registration changes
    private final Object updateLock = new Object();

    // number of file descriptors with registration changes pending
    private int updateCount;

    // file descriptors with registration changes pending
    private int[] updateDescriptors = new int[INITIAL_PENDING_UPDATE_SIZE];

    // events for file descriptors with registration changes pending, indexed
    // by file descriptor and stored as bytes for efficiency reasons. For
    // file descriptors higher than MAX_UPDATE_ARRAY_SIZE (unlimited case at
    // least) then the update is stored in a map.
    // 使用数组保存事件变更, 数组的最大长度是MAX_UPDATE_ARRAY_SIZE, 最大64*1024
    private final byte[] eventsLow = new byte[MAX_UPDATE_ARRAY_SIZE];
    // 超过数组长度的事件会缓存到这个map中，等待下次处理
    private Map<Integer,Byte> eventsHigh;

    // Used by release and updateRegistrations to track whether a file
    // descriptor is registered with epoll.
    private final BitSet registered = new BitSet();


    EPollArrayWrapper() throws IOException {
        // creates the epoll file descriptor
        //创建了epoll文件描述符
        //epollCreate navative的实现在EPollArrayWrapper.c
        epfd = epollCreate();

        // the epoll_event array passed to epoll_wait
        //构建了一个用于存放epoll_wait返回结果的epoll_event数组
        int allocationSize = NUM_EPOLLEVENTS * SIZE_EPOLLEVENT;
        pollArray = new AllocatedNativeObject(allocationSize, true);
        pollArrayAddress = pollArray.address();

        // eventHigh needed when using file descriptors > 64k
        if (OPEN_MAX > MAX_UPDATE_ARRAY_SIZE)
            eventsHigh = new HashMap<>();
    }

    void initInterrupt(int fd0, int fd1) {
        outgoingInterruptFD = fd1;
        incomingInterruptFD = fd0;
        epollCtl(epfd, EPOLL_CTL_ADD, fd0, EPOLLIN);
    }

    void putEventOps(int i, int event) {
        int offset = SIZE_EPOLLEVENT * i + EVENT_OFFSET;
        pollArray.putInt(offset, event);
    }

    void putDescriptor(int i, int fd) {
        int offset = SIZE_EPOLLEVENT * i + FD_OFFSET;
        pollArray.putInt(offset, fd);
    }

    int getEventOps(int i) {
        int offset = SIZE_EPOLLEVENT * i + EVENT_OFFSET;
        return pollArray.getInt(offset);
    }

    int getDescriptor(int i) {
        int offset = SIZE_EPOLLEVENT * i + FD_OFFSET;
        return pollArray.getInt(offset);
    }

    /**
     * Returns {@code true} if updates for the given key (file
     * descriptor) are killed.
     */
    private boolean isEventsHighKilled(Integer key) {
        assert key >= MAX_UPDATE_ARRAY_SIZE;
        Byte value = eventsHigh.get(key);
        return (value != null && value == KILLED);
    }

    /**
     * Sets the pending update events for the given file descriptor. This
     * method has no effect if the update events is already set to KILLED,
     * unless {@code force} is {@code true}.
     */
    private void setUpdateEvents(int fd, byte events, boolean force) {
        if (fd < MAX_UPDATE_ARRAY_SIZE) {
            if ((eventsLow[fd] != KILLED) || force) {
                eventsLow[fd] = events;
            }
        } else {
            Integer key = Integer.valueOf(fd);
            if (!isEventsHighKilled(key) || force) {
                eventsHigh.put(key, Byte.valueOf(events));
            }
        }
    }

    /**
     * Returns the pending update events for the given file descriptor.
     */
    private byte getUpdateEvents(int fd) {
        if (fd < MAX_UPDATE_ARRAY_SIZE) {
            return eventsLow[fd];
        } else {
            Byte result = eventsHigh.get(Integer.valueOf(fd));
            // result should never be null
            return result.byteValue();
        }
    }

    /**
     * 首先会判断存放fd的数组updateDescriptors是否已满，如果满了，则进行扩容，
     * 在原来的基础上加64，然后将fd保存到数组中。然后调用setUpdateEvents，设置fd的事件
     * Update the events for a given file descriptor
     */
    void setInterest(int fd, int mask) {
        synchronized (updateLock) {
            // record the file descriptor and events
            int oldCapacity = updateDescriptors.length;
            if (updateCount == oldCapacity) {
                int newCapacity = oldCapacity + INITIAL_PENDING_UPDATE_SIZE;
                int[] newDescriptors = new int[newCapacity];
                System.arraycopy(updateDescriptors, 0, newDescriptors, 0, oldCapacity);
                updateDescriptors = newDescriptors;
            }
            updateDescriptors[updateCount++] = fd;

            // events are stored as bytes for efficiency reasons
            byte b = (byte)mask;
            assert (b == mask) && (b != KILLED);
            setUpdateEvents(fd, b, false);
        }
    }

    /**
     * Add a file descriptor
     */
    void add(int fd) {
        // force the initial update events to 0 as it may be KILLED by a
        // previous registration.
        synchronized (updateLock) {
            assert !registered.get(fd);
            //EpollWrapper的add方法内部调用了setUpdateEvents方法，并且把第二个参数事件类型（events）设置为0。
            // 在setUpdateEvents中，把fd作为数组的下表，值为事件类型。如果fd大于64*1024,则把fd和事件类型存入eventsHigh中
            setUpdateEvents(fd, (byte)0, true);
        }
    }

    /**
     * Remove a file descriptor
     */
    void remove(int fd) {
        synchronized (updateLock) {
            // kill pending and future update for this file descriptor
            setUpdateEvents(fd, KILLED, false);

            // remove from epoll
            if (registered.get(fd)) {
                epollCtl(epfd, EPOLL_CTL_DEL, fd, 0);
                registered.clear(fd);
            }
        }
    }

    /**
     * Close epoll file descriptor and free poll array
     */
    void closeEPollFD() throws IOException {
        FileDispatcherImpl.closeIntFD(epfd);
        pollArray.free();
    }

    int poll(long timeout) throws IOException {
        //updateRegistrations()方法会将已经注册到该selector的fd和事件
        // (eventsLow或eventsHigh)通过调用epollCtl(epfd, opcode, fd, events),注册到linux系统中
        updateRegistrations();

        //epollWait就会调用linux底层的epoll_wait方法，并返回在epoll_wait期间有事件触发的entry的个数
        updated = epollWait(pollArrayAddress, NUM_EPOLLEVENTS, timeout, epfd);
        for (int i=0; i<updated; i++) {
            if (getDescriptor(i) == incomingInterruptFD) {
                interruptedIndex = i;
                interrupted = true;
                break;
            }
        }
        return updated;
    }

    /**
     * Update the pending registrations.
     */
    private void updateRegistrations() {
        synchronized (updateLock) {
            int j = 0;
            while (j < updateCount) {
                int fd = updateDescriptors[j];
                // 从保存的eventsLow和eventsHigh里取出事件
                short events = getUpdateEvents(fd);
                boolean isRegistered = registered.get(fd);
                int opcode = 0;

                if (events != KILLED) {
                    // 判断操作类型以传给epoll_ctl
                    // 没有指定EPOLLET事件类型
                    if (isRegistered) {
                        opcode = (events != 0) ? EPOLL_CTL_MOD : EPOLL_CTL_DEL;
                    } else {
                        opcode = (events != 0) ? EPOLL_CTL_ADD : 0;
                    }
                    if (opcode != 0) {
                        // 熟悉的epoll_ctl
                        epollCtl(epfd, opcode, fd, events);
                        if (opcode == EPOLL_CTL_ADD) {
                            registered.set(fd);
                        } else if (opcode == EPOLL_CTL_DEL) {
                            registered.clear(fd);
                        }
                    }
                }
                j++;
            }
            updateCount = 0;
        }
    }

    // interrupt support
    private boolean interrupted = false;

    public void interrupt() {
        interrupt(outgoingInterruptFD);
    }

    public int interruptedIndex() {
        return interruptedIndex;
    }

    boolean interrupted() {
        return interrupted;
    }

    void clearInterrupted() {
        interrupted = false;
    }

    static {
        IOUtil.load();
        init();
    }

    /**
     * 创建一个epoll fd,并开辟epoll自己的内核高速cache区，建立红黑树，分配好想要的size的内存对象，建立一个list链表，用于存储准备就绪的事件。
     * @return
     */
    private native int epollCreate();

    /**
     * 对新旧事件进行新增修改或者删除
     * @param epfd
     * @param opcode
     * @param fd
     * @param events
     */
    private native void epollCtl(int epfd, int opcode, int fd, int events);

    /**
     * 等待内核返回IO事件
     * @param pollAddress
     * @param numfds
     * @param timeout
     * @param epfd
     * @return
     * @throws IOException
     */
    private native int epollWait(long pollAddress, int numfds, long timeout,
                                 int epfd) throws IOException;
    private static native int sizeofEPollEvent();
    private static native int offsetofData();
    private static native void interrupt(int fd);
    private static native void init();
}
