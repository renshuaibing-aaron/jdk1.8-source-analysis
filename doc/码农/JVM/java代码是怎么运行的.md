1.首先明白jvm的类加载器有几种，都加载什么？
   类加载器大致分为三种根加载器(bootstrap class loader)它用来加载 Java 的核心类，是用原生代码来实现的，并不继承自 
     java.lang.ClassLoader（负责加载$JAVA_HOME中jre/lib/rt.jar里所有的class，由C++实现，不是ClassLoader子类）。
     由于引导类加载器涉及到虚拟机本地实现细节，开发者无法直接获取到启动类加载器的引用，所以不允许直接通过引用进行操作
     
   扩展类加载器（extensions class loader）：它负责加载JRE的扩展目录，lib/ext或者由java.ext.dirs系统属性指定的目录中的JAR包的类。
     由Java语言实现，父类加载器为null。
     
   系统类加载器（system class loader）：被称为系统（也称为应用）类加载器，它负责在JVM启动时加载来自Java命令的-classpath选项、
     java.class.path系统属性，或者CLASSPATH换将变量所指定的JAR包和类路径。程序可以通过ClassLoader的静态方法getSystemClassLoader()来获取系统类加载器。
     如果没有特别指定，则用户自定义的类加载器都以此类加载器作为父加载器。由Java语言实现，父类加载器为ExtClassLoader
     
   用户自定义实现的加载器
   
   
   
   
2.一个Java 是怎么执行的？

3.关于JVM的内存结构
程序计数器 本地方法栈 虚拟机栈 堆 方法区(Metaspace)  

说明哪些是线程共享的 哪些是非线程共享的  说明一个Java文件编译成class文件 其实是变成了一条条的字节码指令

然后字节码指令 其实对应一条机器指令 

方法在执行过程中其实就是 一条条的指令不断进出虚拟机栈的过程 虚拟机栈里面保存的有局部变量

一个误区
程序计数器是线程私有的 每个线程在执行过程中 都有自己对应的程序计数器



4.类初始化的时机
一个类加载后不一定会被初始化  因为初始化是有时机的
 当创建某个类的新实例时（如通过new或者反射，克隆，反序列化等）
 当调用某个类的静态方法时
 当使用某个类或接口的静态字段时
 调用Java API中的某些反射方法时，比如类Class中的方法，或者java.lang.reflect中的类的方法时
 当初始化某个子类时
 当虚拟机启动某个被标明为启动类的类（即包含main方法的那个类） 所以System.ou.println(Test.class)不满足上面6种情况，也就没有做初始化


