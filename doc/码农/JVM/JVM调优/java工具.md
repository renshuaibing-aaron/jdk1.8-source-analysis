数据如果出现了阻塞，你是怎么排查的，top和jstack命令用过没，jstack命令的nid是什么意思，怎么查看java某个进程的线程
https://www.cnblogs.com/c-xiaohai/p/12489336.html
1.查看系统跟内存溢出的相关的溢出日志
  dmesg |grep -E ‘kill|oom|out of memory’，可以查看操作系统启动后的系统日志
  
2.查看进程PID 
  ps -aux|grep java命令查看一下你的java进程，就可以找到你的java进程的进程id。 

3.使用top命令查看进程对内存的使用率
  top命令显示的结果列表中，会看到%MEM这一列，这里可以看到你的进程可能对内存的使用率特别高。以查看正在运行的进程和系统负载信息，包括cpu负载、内存使用、各个进程所占系统资源等
  
4.使用jstat 命令查看指定进程的统计信息
  用jstat -gcutil 20886 1000 10命令，就是用jstat工具，对指定java进程（20886就是进程id，通过ps -aux | grep java命令就能找到），按照指定间隔，看一下统计信息，这里会每隔一段时间显示一下，包括新生代的两个S0、s1区、Eden区，以及老年代的内存使用率，还有young gc以及full gc的次数
  
5.使用jmap命令查看
  执行jmap -histo pid可以打印出当前堆中所有每个类的实例数量和内存占用
  把当前堆内存的快照转储到dumpfile_jmap.hprof文件中，然后可以对内存快照进行分析
  
6.使用jstack 命令查看进程中的所有线程信息  是否发生死锁等等

7.根据第五步的内存快照找专业的工具进行分析 比如Eclipse MAT  或者jvisualvm.exe 对内存快照进行分析 查看具体哪个对象占用的内存太大



1.生产环境额JVM参数是怎么设置的 为什么这么设置？
-Xmx1024m -Xms1024m -XX:+PrintGCDetails -XX:+PrintGC() -XX:+HeapDumpOnOutOfMemoryError -
Xloggc:/opt/logs/gc.log -XX:HeapDumpPath=/opt/logs/dump

如果minor gc和 full gc 都是遍历线程栈和方法区的gc roots去追踪存活对象，那么full gc的时候岂不是做了minor gc的一些活，清理了一些年轻代的短暂对象？
这与full GC只负责老年代有点冲突了，还是说在遍历GC roots的时候是可以区分新生代还是老年代的？
gc roots可能会遍历到年轻代和老年代，但是回收老年代的时候只会回收老年代里的垃圾对象，遍历和回收是两回事儿


2.生产环境中的JVM优化经验聊聊？

  怎么优化 使用MAT进行内存快照分析
           gc日志 可以查看gc的yongGC 和fullGC的频率
           使用jstat分析 查看
           
3.生产环境中的JVM OOM问题
  消息队列消费问题 rocketMQ消息数据  本地构造一个队列由于队列使用不当(无界队列) 由于是批量接收数据 导致内存中数据
  量过大 然后内存溢出 解决办法是 使用有界队列
  
  
4.在JVM调优过程中的命令参数
怎么查看Java文件编译后的字节码 javap -c 类名
  
  

