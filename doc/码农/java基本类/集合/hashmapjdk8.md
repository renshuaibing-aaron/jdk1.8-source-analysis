1.学习hashmap8必要的基础知识背景
  hashcode  equals == 这个三个方法的区别
    ==  二元运算符 对于基础数据 比较的是值是否相等 对于引用类型 比较的是内存的地址
    equals  object带的方法 可以看出在object里面是用的== 这个可以被重写 
   hashcode  object带的方法 native方法 返回的值内存地址 根据Java 规范 equals相等则hashcode必须相等 反之不成立
   这是因为在hashmap 里面假如equals相等 每次put的位置都不一样 还特码怎么找到
   
  hashcode方法可以重写 怎么写  在String 中是这么实现的 (注意需要明确的是equals相等hashcode必须等 反之不成立)
  ```java
    public int hashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }
```
2.一些二进制位算法
 << : 左移运算符，num << 1,相当于num乘以2  低位补0
 >> : 右移运算符，num >> 1,相当于num除以2  高位补0
 >>> : 无符号右移，忽略符号位，空位都以0补齐
 % : 模运算 取余
 ^ :   位异或 第一个操作数的的第n位于第二个操作数的第n位相反，那么结果的第n为也为1，否则为0
 & : 与运算 第一个操作数的的第n位于第二个操作数的第n位如果都是1，那么结果的第n为也为1，否则为0
 | :  或运算 第一个操作数的的第n位于第二个操作数的第n位 只要有一个是1，那么结果的第n为也为1，否则为0
 ~ : 非运算 操作数的第n位为1，那么结果的第n位为0，反之，也就是取反运算（一元操作符：只操作一个数）
 
 在hashap里面为了更好的散列 解决hash冲突 在原来的基础上进行了重写
  无符号右移16位的基础上 进行与原来的hashcode进行异或运算  答案简单来说就是为了 充分利用这32位的数据性质 减少hash碰撞
 ```java
 static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

```

同理 在hashmap进行get的时候查数组位置的时候 同样进行了 优化
  一般意义上我们应该使用的是tab[(n) %hash   可见下面这两个的意义其实是一样的  但是优化是显而易见的与运算的要比%高 但是在数学上这就要求
  n的值必须是2的n次幂！！
```java
  tab[(n - 1) & hash
```
 
 
 
2.hashmap的扩容时机
   第一在hash碰撞后 进行插入 然后是链表对 链表进行遍历 尾插入后进行判断链表的长度 此时假如链表>8  并且table的长度，注意是table的长度小于64
   进行扩容 
   第二个时机是在 进行插入后 判断整个hashmap的size  假如size的值大于阈值 那么进行扩容
   
3.说说hashmap的负载因子和阈值的计算方式
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
  