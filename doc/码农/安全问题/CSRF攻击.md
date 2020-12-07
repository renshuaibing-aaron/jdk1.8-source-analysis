1.什么是CSRF攻击？
简单来说攻击者盗用你的身份 以你的名义发送恶意请求
2.主要解决方案
利用HTTP 协议头的 Referer 字段进行检验  
随机token进行检验
https://www.cnblogs.com/hyddd/archive/2009/04/09/1432744.html
https://www.cnblogs.com/xieze/p/7995156.html
https://segmentfault.com/a/1190000016659945