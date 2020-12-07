https 的加密过程（ SSL 四次握手）？对称加密和非对称加密有什么区别？


讲讲TCP三次握手和四次挥手。
TCP三次握手这种机制你觉得会出现什么安全上的问题。
对DDoS了解吗？讲讲你所知道的DDoS攻击方式以及防护措施。
了解过C++网络编程吗，讲讲你所知道的套接字选项。
讲讲select,poll和epoll的实现原理。
http2.0新特性,了解http3.0吗。
讲讲https的握手。和http相比，https有什么优缺点。
http1.0，http1.1，http2
https握手

若建立连接时间比较长，HTTP是怎么优化的（注意是HTTP不是TCP，可以参考下：

13. http报文头结构
    http的头部可以传输二进制数据吗？
    

14. http报文长度边界字段
 time-wait和close-wait
 
 cookie在http报文的那个位置
 https://www.cnblogs.com/imstudy/p/10669631.html
 
 字符流、字节流、二进制及其在HTTP协议传输
 
 https://www.cnblogs.com/doit8791/p/7664961.html
 
 作者：蓦然回首丶
 链接：https://www.nowcoder.com/discuss/302831?from=zhnkw
 来源：牛客网
 
 一.计算机网络
 ①http和https有什么区别？
 没回答出来。https是ssl(安全套接层+http，加密版本)
 ②数字证书有哪些？
 没看过这个知识点，讲了一下公钥体系。
 ③http有哪些版本，区别？
 0.9/1.0/1.1/2.0，我说成1.2而且只知道持久连接。
 0.9是原始版本，只有get操作。
 1.0新增了post，head操作，增加了状态码等。一般是一个请求一个连接。有keepalive。
 1.1增加了持久连接，全二进制，管道，put，delete，options等请求方式。
 2.0增加了双工，数据流等。
 ④TCP和IP属于什么层？TCP挥手握手？
 终于有个会的了
 ⑤TCP可靠传输的机制？窗口的含义是什么？
 回答窗口协议，可重传，连接建立。
