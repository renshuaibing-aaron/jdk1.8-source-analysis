1.什么是XSS攻击？
简单的说XSS攻击就是想办法教唆浏览器去执行一个网页中不存在的前端代码

2.解决方案？
  首先是过滤。对诸如<script>、<img>、<a>等标签进行过滤。
  其次是编码。像一些常见的符号，如<>在输入的时候要对其进行转换编码，这样做浏览器是不会对该标签进行解释执行的，同时也不影响显示效果。
  最后是限制。通过以上的案例我们不难发现xss攻击要能达成往往需要较长的字符串，因此对于一些可以预期的输入可以通过限制长度强制截断来进行防御。
  
https://zhuanlan.zhihu.com/p/26177815
https://tech.meituan.com/2018/09/27/fe-security.html
https://blog.csdn.net/qw_xingzhe/article/details/80712840