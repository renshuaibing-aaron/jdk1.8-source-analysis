1.这里面最复杂的HashMap


哪些Map的可以实现有序的？
TreeMap和LinkedHashMap 

TreeMap是通过实现SortMap接口，
能够把它保存的键值对根据key排序，基于红黑树，从而保证TreeMap中所有键值对处于有序状态

LinkedHashMap则是通过插入排序（就是你put的时候的顺序是什么，取出来的时候就是什么样子）
和访问排序（改变排序把访问过的放到底部）让键值有序。


2.关于HashMap和HashTable 键和值 为null情况
HashMap可以为空 hashTable不能 这里面涉及到equal()方法


3.HashMap put过程
1、对Key求Hash值，然后再计算下标

2、如果没有碰撞，直接放入桶中（碰撞的意思是计算得到的Hash值相同，需要放到同一个bucket中）

3、如果碰撞了，以链表的方式链接到后面

4、如果链表长度超过阀值( TREEIFY THRESHOLD==8)，就把链表转成红黑树，链表长度低于6，就把红黑树转回链表

5、如果节点已经存在就替换旧值

6、如果桶满了(容量16*加载因子0.75)，就需要 resize（扩容2倍后重排）


4.什么是扰动函数？
 为了充分利用hashcode的每一位 让hash更加均匀 较少hash碰撞


5.为什么使用红黑树 不使用平衡二叉树？
  因为平衡二叉树的插入比较复杂 在插入时旋转更加难以平衡和调试 所以在hashmap中采取红黑树


6.都说HashMap 线程不安全 哪里不安全？
https://blog.csdn.net/linsongbin1/article/details/54708694
https://blog.csdn.net/zhangjunli/article/details/80653283
https://www.cnblogs.com/developer_chan/p/10450908.html
https://blog.csdn.net/swpu_ocean/article/details/88917958
HashMap 的线程不安全表现在HashMap在并发环境下多线程put后可能导致get死循环，具体表现为CPU使用率100%，看一下transfer的过程，就是在扩容的过程中产生闭环 导致问题
另外一个明显的不安全是在put的过程中可能导致数据的丢失 因为没有加锁
具体来说：
在jdk1.7中 HashMap的插入采用的是头插法  在扩容的时候采用头插法会导致死循环(当然是在多线程并发的情况下)  会导致CPU高
在put 操作的时候 两个线程同时put 假如hash 值是同一个 这时候进行put 会出现第二个的值直接把第一个的值给覆盖了(本来应该是出现hash碰撞 两个节点的)
特别说明的是 在jdk1.8中已经把链表中插入节点改为尾插法 这意味着在JDK1.8中不会出现死循环 但是第二个问题 依旧会出现(这里需要加锁来实现)
在浏览其他的一些博客的时候 有人说是hashmap的值线程不安全是put之后 无法被其他线程感知到 或者是在put的时候不能被另外一个线程get到 其实这个不是线程不安全的保证
线程不安全 这里是强一致性或者是弱一致性的问题 看concurrenthashMap 其实concurrenthashMap 也是弱一致性  只有hashtable是强一致性的


7.hashmap在jdk1.8之前采用的是头插法  之后用的是尾插法 原因是什么？？？？
  头插法在两个线程同时扩容的时候 会造成环状  然后进行get的时候 导致死循环
  https://blog.csdn.net/HNUST_LIZEMING/article/details/89334204


8.重点学习ConcurrentHashMap的扩容方法 这个方法的大致过程 通过两个标志位进行扩容  多线程  在扩容过程中插入 会进行阻塞插入 然后会进行帮助扩容操作

























