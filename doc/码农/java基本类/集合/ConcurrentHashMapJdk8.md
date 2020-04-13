1.JDK 8的实现

扩容实现：
扩容实现是JDK8中concurrentHashMap中比较牛逼的实现 首先明白 对于ConcunrrentHashMap  由于数组的长度是2的幂次倍 导致在扩容后每个桶的索引要么在原位置
要么在+n的位置 这就为分治创造了机会 因为每个桶里面的东西的位置都是一定的

2.为什么要使用2的幂的大小


3.这个map中计算hash值的方法(充分利用高32位和低32位)？ 计算索引值的方法(异或运算) ? 为什么不采用取模的方法  这也是为什么要2的幂的原因



 
 
5.另外 ConcurrentHashMap 使用了一种不同的迭代方式。在这种迭代方式中，当 iterator 被创建后集合再
发生改变就不再是抛出 ConcurrentModificationException 异常，取而代之的是在改变时 new 新的数据从而
不影响原有的数据，
iterator 完成后再将头指针替换为新的数据 ，这样 iterator 线程可以使用原来老的数据，而写线程也可以并发的完成改变。 




6.concurrentHashMap读 不用加锁的原因？
在 JDK7 以及以前
HashEntry 中的 key、hash、next 均为 final 型，只能表头插入、删除结点。
HashEntry 类的 value 域被声明为 volatile 型。
不允许用 null 作为键和值，当读线程读到某个 HashEntry 的 value 域的值为 null 时，便知道产生了冲突——发生了重排序现象（put 方法设置新 value 对象的字节码指令重排序），需要加锁后重新读入这个 value 值。
volatile 变量 count 协调读写线程之间的内存可见性，写操作后修改 count ，读操作先读 count，根据 happen-before 传递性原则写操作的修改读操作能够看到。
在 JDK8 开始
Node 的 val 和 next 均为 volatile 型。
tabAt(..,) 和 casTabAt(...) 对应的 Unsafe 操作实现了 volatile 语义
 
7.值得注意的是在concurrentHashMap中共有5种节点类型 
    1、Node节点，是所有节点的父类，可以单独放入桶内，也可以作为链表的头放入桶内。
    2、TreeNode节点，继承自Node，是红黑树的节点，此节点不能直接放入桶内，只能是作为红黑树的节点。
    3、TreeBin节点，TreeNode的代理节点，可以放入桶内，这个节点下面可以连接红黑树的根节点，所以叫做TreeNode的代理节点。
    4、ForwardingNode节点，扩容节点，只是在扩容阶段使用的节点，当前桶扩容完毕后，桶内会放入这个节点，此时查询会跳转到查询扩容后的table，不存储实际数据
       这就是个辅助类 并且类的存活在扩容期间 只是一个标志 指向nextTable 提供find方法 hash值为-1 key value next为空
    5、ReservationNode节点，内部方法使用
    
8.sizeCtl的作用
  控制标识符，用来控制table初始化和扩容操作的，在不同的地方有不同的用途，其值也不同，所代表的含义也不同
  负数代表正在进行初始化或扩容操作
  -1代表正在初始化
  -N 表示有N-1个线程正在进行扩容操作
  正数或0代表hash表还没有被初始化，这个数值表示初始化或下一次进行扩容的大小  其实就是阈值
  
  
9.根据concurrentHashmap的put过程
  
  1)判空,concurrenthasmap的key和value都不许为空
  2)计算hash值
  3)遍历table 进行节点的插入 整个插入的过程是个自旋的过程 中间不断的刷新table
     如果table为空 表示没有初始化  首先进行初始化操作inittable()  这里怎么解决线程安全 利用CAS 操作sizeCtl 值进行控制
     根据hash的值获取桶(table节点)的位置 位置为空直接插入  整个过程不需要加锁 
     桶的位置不为空(锁节点) 检查节点的hash值 如果是hash值是-1 表示的是ForwardingNode节点 表示有线程正在扩容 则帮助现线程一起记性扩容
                  hash值大于等于0表示是链表 遍历链表尾插入  插入后判断是否需要扩容或者是红黑树的转化
                  hash的值是-2 是红黑树 进行红黑树的插入
                  
   4)更新size的值
     
10.扩容方法
 首先扩容的时机 有两个地方
  在插入的过程中 在判断是否转变为红黑树的时候 会进行判断是否扩容
   扩容的时机在put进入后 发现链表的节点数据超过一定的阈值 会触发红黑树的转换 这个时候如果发现 桶的数量小于64则首选扩容  如果桶的大小大于64则进行红黑树的转换
  每次插入后会进行判断是否进行扩容
 扩容的过程 
 其实扩容的方法 很有技巧 比如为什么采用2的倍数作为数组的长度 就是在扩容的过程中会方便找出新的数组的位置
 并发进行扩容采用的 sizeCtl 和ForwardingNode 进行控制和synchronized进行控制
 https://www.jianshu.com/p/f6730d5784ad
  
  
11.size的操作

JDK1.7和JDK1.8 对size的计算是不一样的 
在1.7种是先不加锁的计算三次  如果三次的结果不一样加锁
在1.8中是通过baseClunt和counterCell进行CAS计算  最终通过baseCount和遍历countsell数组得出size
在1.8中推荐使用mappingcount方法 这个方法的返回值是long类型 不会因为size方法是int类型限制最大值

































  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
 
 
 
