2.threadlocal常见的应用
在web项目里面  可以存放每次请求的用户信息

2.threadlocal的底层数据结构 需要首先了解java中的弱引用的知识
  
3.threadlocal的内存泄露的原因 已经  



自己的理解 ：Thread线程内部有个ThreadLocalMap 这个map中存放的是Entry(注意的是这个Entry是弱引用类型)并且引用的对象是
ThreadLocal 底层原理是 由线程对象获取到这个map  然后利用threadlocal 这个key找到entry ,那么为什要使用弱引用呢？
假如不使用弱引用  一般情况下 map 中的entry中的key 是强引用  假如是强引用 ,threadlocal 应用被释放后 因为threadlocal对象被threadlocalMap 所强引用
导致threadlocal 这个对象无法释放 造成内存泄漏 (很多博客写是是value泄漏 ，个人觉得这是 不对的)  所以需要使用弱引用
```java
    static class Entry extends WeakReference<ThreadLocal<?>> {
            /** The value associated with this ThreadLocal. */
            Object value;

            Entry(ThreadLocal<?> k, Object v) {
                super(k);   //这个引用是弱引用
                value = v;
            }
        }
```