1.Netty中的零拷贝体现在三个方面
https://segmentfault.com/a/1190000021490174
  buffer层面：对于ByteBuf Netty提供了多种实现
  Heap ByteBuf直接在堆内存分配
  Direct ByteBuf直接在内存分配不是堆内存 这个是零拷贝实现
  CompositeByteBuf 组合Buffer 是零拷贝
 在操作系统层面 Netty的文件传输采用的是transferTo方法 可以直接将文件缓存区中的数据发送到目标channel 避免了传统通过循环write方式导致的内存拷贝问题
 