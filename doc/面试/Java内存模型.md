1.谈谈你对Java内存模型的理解可以吗
 java 内存模型和JVM内存模型不一样 注意区分
 
 2.你知道Java内存模型中的原子性、有序性、可见性是什么吗
 
 java内存模型->原子性，可见性，有序性->volatile->happens-befores/内存屏障
 
 3.能从Java底层角度聊聊volatile关键字的原理吗
 主要用来解决可见性和有序性  在一定条件下可以解决原子性   
 volatile 这个关键字 可以保证可见性和禁止指令重排
 
 4.你知道指令重排以及happens-before原则是什么吗？
 
 5.volatile底层是如何基于内存屏障保证可见性和有序性的
 