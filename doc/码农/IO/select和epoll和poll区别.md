1.要弄懂select和epoll和poll的区别首先需要知道的IO模型
根据UNIX网络编程对IO模型的分类UNIX提供了5种IO模型
  阻塞IO：
  非阻塞IO:
  I/O复用模型：
  信号驱动I/O模型：
  异步IO：
  
2.其中select和epoll和poll是这五种IO模型种IO复用理论的实践
但是在linux种并不支持poll模型
select的问题：
1.每次select都需要将需要监控的文件描述符集合从用户态copy到内核态，内核并将ready的描述符集合再从内核态copy到用户态，如果socket很大，会有很大的上下文切换的损耗。
2.由于readfds是长度为32的整型数组，32*32=1024，bitmap机制来表示的fd最多可表示1024个，socket连接有上限
3.每次都是O(n)复杂度遍历所有socket收集有事件的socket。
4.每次都是O(n)复杂度（n是最大的fd值）遍历从内核态返回来的ready的fdset


poll:(注意在linux上不支持)

1.poll不需要每次都重新构建需要监控的fd set，但还是会有引起上下文切换的内存copy
2.poll不需要像select那样需要用户计算fd的最大值+1，作为select函数的第一个参数
3.poll减少了fd的遍历，在select中监控的某socket所对应的fd值为1000，那么需要做1000次循环
4.poll 解除了select对于fd数量1024的限制
5.poll在unix下不支持

epoll模型：
1.epoll 减少了用户态和内核态间的内存copy
2.epoll有着高效的fd操作的红黑树结构
3.epoll基本没有fd数量限制
4.epoll每次只需遍历ready_list中就绪的socket即可

参考资料：
https://blog.csdn.net/lsgqjh/article/details/86622532