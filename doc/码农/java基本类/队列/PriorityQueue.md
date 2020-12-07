1.堆结构的优秀实现类----PriorityQueue优先队列

https://blog.csdn.net/qq_35326718/article/details/72866180
https://blog.csdn.net/u010623927/article/details/87179364

2.堆的底层实现是数组+完全二叉树
    leftNo = parentNo*2+1
    rightNo = parentNo*2+2
    parentNo = (nodeNo-1)/2

PriorityQueue 在遍历输出的时候 并不会像LinkList一样从头到尾进行输出 而是先输出根的值，然后不断的调整堆的结构 使之继续成为一棵完全二叉树的结构


3.插入元素
 add  
 offer
 两个方法都表示往队列里添加元素 但是当出现异常时，add方法抛出异常 而offer则返回的是false，就是啥事也没有，也不抛异常，也没有添加成功
 
4.删除元素 怎么调整结构