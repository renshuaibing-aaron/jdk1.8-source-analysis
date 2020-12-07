https://blog.csdn.net/qq_34902684/article/details/85538895
https://blog.csdn.net/w372426096/article/details/81631564
1.java 是个解释编译性质的语言 java文件编译成class文件 经JVM 编译或者解释为机器语言 让计算机执行
之前JVM是解释 解释的意思是一条一条的执行 这样效率太慢 JVM采用JIT即时编译 

jvm参数：-server -client  这个就是说 JVM以什么模式启动 客户端还是服务端
https://developer.ibm.com/zh/articles/j-lo-just-in-time/


JDK动态代理的的性能没有cglib的高的原因是因为 JDK的动态代理底层使用的是JDK的反射实现
反射比较慢的原因是因为反射无法使用JIT即时编译优化