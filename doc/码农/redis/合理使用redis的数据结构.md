1.对于一个有数亿用户的网站，如何统计它的用户访问数？
  共有三种方式 hash bitset HyperLogLog  
https://mp.weixin.qq.com/s?__biz=MzU2Njg3OTU1Mg==&mid=2247484953&idx=1&sn=3920e169658fa56ec1f7b0f8ba32adae&chksm=fca4f236cbd37b209d3fe9961541104e1206ce686859e618bc8bdb4cd75560ce664ea0034bed&scene=27#wechat_redirect 



2.合理使用redis的数据设计一个方法 进行网站的限流
 简单的实现用string  复杂的使用hash
 
 
 3.合理使用redis的SortedSet 类型实现一个消息推送的 