package aaron.ren.jvm.sdkspi;
/*
*
* 如果说SPI是java提供的一种拓展机制, 其实是不明确的, 结合java本身的语言特性来说,
* SPI直观的看就是 基于接口的编程 + 策略模式 + 配置文件 组合实现的动态加载机制, 用大白话解释就是说,
* 一个框架的设计为了后期的拓展性, 肯定先会在顶层设计接口, 然后再为这些接口提供一些默认的实现类, 未了良好的拓展性,
*  如果想让, 如果想实现允许当前框架 识别 / 加载 / 使用 第三方提供的jar包时 , 就可以使用SPI实现接口的动态加载,
* 只要遵循SPI的规范, java就能将我们自己的类也加载进JVM供我们使用
*
*
* 打破双亲委派模型  可以用根加载器加载 用户自定义的类
* */