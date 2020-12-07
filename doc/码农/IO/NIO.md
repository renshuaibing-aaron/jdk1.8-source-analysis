java nio相关知识放到netty里面
NIO的三大组件：Buffer,Channel,Selector


2.一旦注册到Selector上，Channel将一直保持注册直到其被解除注册。在解除注册的时候会解除
Selector分配给Channel的所有资源。 也就是SelectionKey在其调用SelectionKey#channel方法，
或这个key所代表的channel 关闭，抑或此key所关联的Selector关闭之前，都是有效。我们在前面的文章
分析中也知道，取消一个SelectionKey，不会立刻从Selector移除，它将被添加到Selector的cancelledKeys这个
Set集合中，以便在下一次选择操作期间删除，
我们可以通过java.nio.channels.SelectionKey#isValid判断一个SelectionKey是否有效


3.Channel接口
  称为通道，用于 I/O 操作的连接类似于原I/O中的流（Stream），但有所区别：
  
  流是单向的，通道是双向的，可读可写。
  流读写是阻塞的，通道可以异步读写。
  流中的数据可以选择性的先读到缓存中，通道的数据总是要先读到一个缓存中，或从缓存中写入。


4.
使用Buffer读写数据一般遵循以下四个步骤：

写入数据到Buffer
调用flip()方法
从Buffer中读取数据
调用clear()方法或者compact()方法
当向buffer写入数据时，buffer会记录下写了多少数据。一旦要读取数据，需要通过flip()方法将Buffer从写模式切换到读模式。在读模式下，可以读取之前写入到buffer的所有数据。

一旦读完了所有的数据，就需要清空缓冲区，让它可以再次被写入。有两种方式能清空缓冲区：调用clear()或compact()方法。clear()方法会清空整个缓冲区。compact()方法只会清除已经读过的数据。任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。


5.比较select() poll  epoll   的区别
select()
   单个进程就可以同时处理多个网络连接的io请求（同时阻塞多个io操作）。基本原理就是程序呼叫select，然后整个程序就阻塞状态，
   这时候，kernel内核就会轮询检查所有select负责的文件描述符fd，   当找到其中那个的数据准备好了文件描述符，
   会返回给select，select通知系统调用，将数据从kernel内核复制到进程缓冲区(用户空间)。
   
   缺点：
    （1）每次调用select，都需要把fd集合从用户态拷贝到内核态，这个开销在fd很多时会很大
    （2）同时每次调用select都需要在内核遍历传递进来的所有fd，这个开销在fd很多时也很大
    （3）select支持的文件描述符数量太小了，默认是1024
 
poll的原理和select() 相似
    描述fd集合的方式不同，poll使用 pollfd 结构而不是select结构fd_set结构，所以poll是链式的，没有最大连接数的限制
    poll有一个特点是水平触发，也就是通知程序fd就绪后，这次没有被处理，那么下次poll的时候会再次通知同个fd已经就绪。
    
    
    
epoll的原理：
    epoll没有fd数量限制
    epoll没有这个限制，我们知道每个epoll监听一个fd，所以最大数量与能打开的fd数量有关，一个g的内存的机器上，能打开10万个左右
    epoll不需要每次都从用户空间将fd_set复制到内核kernel
    epoll在用epoll_ctl函数进行事件注册的时候，已经将fd复制到内核中，所以不需要每次都重新复制一次
    select 和 poll 都是主动轮询机制，需要遍历每一个人fd；
    epoll是被动触发方式，给fd注册了相应事件的时候，我们为每一个fd指定了一个回调函数，当数据准备好之后，就会把就绪的fd加入一个就绪的队列中，epoll_wait的工作方式实际上就是在这个就绪队列中查看有没有就绪的fd，如果有，就唤醒就绪队列上的等待者，然后调用回调函数。
    虽然epoll。poll。epoll都需要查看是否有fd就绪，但是epoll之所以是被动触发，就在于它只要去查找就绪队列中有没有fd，就绪的fd是主动加到队列中，epoll不需要一个个轮询确认。
    换一句话讲，就是select和poll只能通知有fd已经就绪了，但不能知道究竟是哪个fd就绪，所以select和poll就要去主动轮询一遍找到就绪的fd。而epoll则是不但可以知道有fd可以就绪，而且还具体可以知道就绪fd的编号，所以直接找到就可以，不用轮询
























