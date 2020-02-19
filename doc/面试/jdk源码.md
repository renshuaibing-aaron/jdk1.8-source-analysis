1.HashMap 1.8 相对于1.7 到底更新了什么？ 
https://www.jianshu.com/p/8324a34577a0

2.Oracle/Sun JDK下的src.zip只带有 java.* / javax.* 的公有API的源码，
sun.nio属于Oracle/Sun JDK的私有API，所以没有包含在src.zip里。
  所谓公有/私有API，此处最大的区别不在于是否开放源码，而在于是否属于Java平台规范的一部分。
  Java标准库的公有API属于规范的一部分，所有符合规范的JDK/JRE实现都必须提供完全一样的Java标准库公有API（只要API兼容即可，不要求内部实现完全一样）。而私有部分可以每个JDK/JRE自行决定API。
  从OpenJDK 7开始，Oracle JDK跟OpenJDK的Java库的大部分源码都是完全一致的。有需要的话请去OpenJDK看源码
  
  
 看来需要研究openjdk代码 这里oracle的代码有些是缺失的  同目录的压缩包  保存的是openjdk的源码 