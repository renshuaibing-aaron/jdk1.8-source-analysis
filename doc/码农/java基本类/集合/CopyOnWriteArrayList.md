CopyOnWriteArrayList的思想和实现整体上还是比较简单，它适用于处理“读多写少”的并发场景。通过上述对CopyOnWriteArrayList的分析，读者也应该可以发现该类存在的一些问题：

1. 内存的使用
由于CopyOnWriteArrayList使用了“写时复制”，所以在进行写操作的时候，内存里会同时存在两个array数组，如果数组内存占用的太大，
那么可能会造成频繁GC,所以CopyOnWriteArrayList并不适合大数据量的场景。

2. 数据一致性
CopyOnWriteArrayList只能保证数据的最终一致性，不能保证数据的实时一致性——读操作读到的数据只是一份快照。所以如果希望写入的数据可以立刻被读到，
那CopyOnWriteArrayList并不适合。



3.https://blog.csdn.net/j080624/article/details/82692326
浅谈从fail-fast机制到CopyOnWriteArrayList使用