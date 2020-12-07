1.首先一个场景 从本地文件读取内容发送到socket上的过程？
https://www.jianshu.com/p/2581342317ce
传统
File.read(file, buf, len);
Socket.send(socket, buf, len);

这个过程其实中间涉及到四次内核态和用户态的转换和四次拷贝（两次CPU拷贝两次DMA拷贝）
  read()---上下文转换 底层采用DMA的方式读取磁盘的文件 并且把内容存储到内核地址的读取缓冲区(OS) 然后调用返回
           上下文转换(内核态->用户态) 第二次拷贝把数据内容转换到用户缓冲区 (此时用户可以进行对数据操作)
  send()--上下文切换(用户态->内核态) 第三次拷贝 把数据拷贝到内核地址空间缓冲区，注意这个缓冲区和目标套接字相关联
          上下文切换, 第四次拷贝 利用DMA把数据从目标套接字相关的缓存区传到协议引擎进行发送
  
  
优化后上下文切换的次数从四次减少到了两次
   数据拷贝次数从四次减少到了三次（其中DMA copy 2次，CPU copy 1次）
   public void transferTo(long position, long count, WritableByteChannel target);
   
   
   
2.
这个sendFile系统调用的底层原理：
通过 sendfile 系统调用，数据可以直接在内核空间内部进行 I/O 传输，从而省去了数据在用户空间和内核空间之间的来回拷贝。
与 mmap 内存映射方式不同的是， 
sendfile 调用中 I/O 数据对用户空间是完全不可见的。也就是说，这是一次完全意义上的数据传输过程。