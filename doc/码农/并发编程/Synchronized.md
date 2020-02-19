1.Synchronized  的基本使用 
 修饰普通方法 
 修饰静态方法
 修饰代码块
 
 2.说明三种使用方式的底层原理
 
 对于修饰的同步代码块进行反编译 明显可以看出 在同步代码块的两端生成了两条jvm指令 
 monitorenter  和monitorexit 
 通过查找JVM规范可以知道 每个对象都有一个监视器锁 monitor 当monitor被占用时 就处于被锁定状态
 说明对于synchronized的语义底层时通过一个monitor来完成的 其实对于wait和notify等方法也是依赖monitor
 
 对于修饰方法的同步 JVM的实现用的不是字节码指令 其常量池中多了ACC_SYNCHRONIZED 来标识
 JVM根据这个标识来实现方法的同步 当方法调用时 检查方法的访问标志是否被设置
 
