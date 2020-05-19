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
不允许用 null 作为键和值，当读线程读到某个 HashEntry 的 value 域的值为 null 时，便知道产生了冲突——发生了重排序现象（put 方法设置新 value 对象的
字节码指令重排序），需要加锁后重新读入这个 value 值。
volatile 变量 count 协调读写线程之间的内存可见性，写操作后修改 count ，读操作先读 count，根据 happen-before 传递性原则写操作的修改读操作能够看到。

在 JDK8 开始
Node 的 val 和 next 均为 volatile 型。
tabAt(..,) 和 casTabAt(...) 对应的 Unsafe 操作实现了 volatile 语义

这里需要注意的是concurrentHashMap 是弱一致性的 也就意味着put的时候 不能被立即get到 想要做到强一致性 可以使用hashTable
https://blog.csdn.net/yu757371316/article/details/81389867

这个地方需要说明的是 在JDK1.8里面 不需要加锁的原因是Node的val和next属性都被volatile修饰了 这里参考上面内容
并且我们知道在concurrentHashmap 里面也有一个table 这个table也被volatile修饰了 但是这个只是保证扩容的时候可见 table扩容
https://www.cnblogs.com/yydcdut/p/3959815.html
https://blog.csdn.net/qq_22343483/article/details/100593928




 
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

      注意和下面的进行整合
             #####
             不允许有 null key 和 null value。
             只有在第一次 put 的时候才初始化 table。初始化有并发控制。通过 sizeCtl 变量判断（小于 0）。
             当 hash 对应的下标是 null 时，使用 CAS 插入元素。
             当 hash 对应的下标值是 forward 时，帮助扩容，但有可能帮不了，因为每个线程默认 16 个桶，如果只有 16个桶，第二个线程是无法帮助扩容的。
             如果 hash 冲突了，同步头节点，进行链表操作，如果链表长度达到 8 ，分成红黑树。
             调用 addCount 方法，对 size 加一，并判断是否需要扩容（如果是覆盖，就不调用该方法）。
             Cmap 的并发性能是 hashTable 的 table.length 倍。只有出现链表才会同步，否则使用 CAS 插入。性能极高。
             
             #####
  
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
        #####
        该方法会根据 CPU 核心数平均分配给每个 CPU 相同数量的桶。但如果不够 16 个，默认就是 16 个。
        扩容是按照 2 倍进行扩容。
        每个线程在处理完自己领取的区间后，还可以继续领取，如果有的话。这个是 transferIndex 变量递减 16 实现的。
        每次处理空桶的时候，会插入一个 forward 节点，告诉 putVal 的线程：“我正在扩容，快来帮忙”。但如果只有 16 个桶，只能有一个线程扩容。
        如果有了占位符，那就不处理，跳过这个桶。
        如果有真正的实际值，那就同步头节点，防止 putVal 那里并发。
        同步块里会将链表拆成两份，根据 hash & length 得到是否是 0，如果是0，放在低位，反之，反之放在 length + i 的高位。这里的设计是为了防止下次取值的时候，hash 不到正确的位置。
        如果该桶的类型是红黑树，也会拆成 2 个，这是必须的。然后判断拆分过的桶的大小是否小于等于 6，如果是，改成链表。
        线程处理完之后，如果没有可选区间，且任务没有完成，就会将整个表检查一遍，防止遗漏
        
        ######



 首先扩容的时机 有两个地方
  在插入的过程中 在判断是否转变为红黑树的时候 会进行判断是否扩容
   扩容的时机在put进入后 发现链表的节点数据超过一定的阈值 会触发红黑树的转换 这个时候如果发现 桶的数量小于64(桶的数量本质是table的某个值)则首选扩容  如果桶的大小大于64则进行红黑树的转换
  每次插入后会进行判断是否进行扩容
 扩容的过程 
 其实扩容的方法 很有技巧 比如为什么采用2的倍数作为数组的长度 就是在扩容的过程中会方便找出新的数组的位置
 并发进行扩容采用的 sizeCtl 和ForwardingNode 进行控制和synchronized进行控制
 https://www.jianshu.com/p/f6730d5784ad
  
  
11.size的操作
####  注意和下面的整合
        size 方法不准确，原因是由于并发插入，baseCount 难以及时更新。计数盒子也难以及时更新。
        内部通过两个变量，一个是 baseCount，一个是 counterCells，counterCells 是并发修改 baseCount 后的备用方案。
        具体更新 baseCount 和 counterCells 是在 addCount 方法中。备用方法 fullAddCount 则会死循环插入。
        CounterCell 是一个用于分配计数的填充单元，改编自 LongAdder和Striped64。内部只有一个 volatile 的 value 变量，同时这个类标记了 @sun.misc.Contended，这是一个避免伪共享的注解，用于替代之前的缓存行填充。多线程情况下，注解让性能提升 5 倍。
#####

JDK1.7和JDK1.8 对size的计算是不一样的 
在1.7种是先不加锁的计算三次  如果三次的结果不一样加锁
在1.8中是通过baseClunt和counterCell进行CAS计算  最终通过baseCount和遍历countsell数组得出size
在1.8中推荐使用mappingcount方法 这个方法的返回值是long类型 不会因为size方法是int类型限制最大值


12 注意的三个博客
https://www.jianshu.com/p/749d1b8db066
https://www.jianshu.com/p/2829fe36a8dd
https://www.jianshu.com/p/39b747c99d32


13 addCount 方法总结 
  这个方法主要是为了让size使用的  会进行两个值的复制操作
       1 当插入结束的时候，会对 size 进行加一。也会进行是否需要扩容的判断。
       2 优先使用计数盒子（如果不是空，说明并发了），如果计数盒子是空，使用 baseCount 变量。对其加 X。
       3 如果修改 baseCount 失败，使用计数盒子。如果此次修改失败，在另一个方法死循环插入。
       4 检查是否需要扩容。
      5  如果 size 大于等于 sizeCtl 阈值，且长度小于 1 << 30，可以扩容成 1 << 30，但不能扩容成 1 << 31。
       6 如果已经在扩容，帮助其扩容，和 helpTransfer 逻辑一样。
      7  如果没有在扩容，自行开启扩容，更新 sizeCtl 变量为负数，赋值为标识符高 16 位 + 2。































  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
 
 
 
