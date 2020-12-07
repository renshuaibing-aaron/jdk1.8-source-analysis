1.三个角色  其中RM和TM都是客户端模式 TC是Server模式
  其中TM 和RM 都是作为客户端和业务系统集成在一起  TC作为Seata的服务端独立进行部署
RM：控制分支事务，资源管理，负责分支注册、状态汇报，并接收事务协调器的指令，驱动分支（本地）事务的提交和回滚 分支事务

TM：TM 是负责整个全局事务的管理器，因此一个全局事务是由 TM 开启的，TM 有个全局管理类 GlobalTransaction  
    其实就是谁标注了注释@GlobalTransactional  
  
TC：事务协调器，维护全局事务的运行状态，负责协调并驱动全局事务的提交或回滚； 本质是Seata服务器
    存储事务的日志 补偿异常的事务 集中管理事务的全局锁

https://developer.aliyun.com/article/698995

https://mp.weixin.q雄安UN扎UNq.com/s/Pypkm5C9aLPJHYwcM6tAtA
http://seata.io/zh-cn/docs/dev/mode/at-mode.html
https://blog.csdn.net/tianyaleixiaowu/article/details/95208906

2.AT模式的核心是对业务无侵入，是一种改进后的两阶段提交，其设计思路如图
  TM开启分布式事务（TM向TC注册全局事务记录）
  按照业务场景  编排数据库 服务等事务内资源（RM向TC汇报资源准备状态）
  TM结束分布式事务 事务一阶段结束（TM通知TC提交/回滚分布式事务）
  TC汇总事务信息 决定分布式事务提交还是回滚
  TC通知所有RM提交/回滚资源 事务二阶段结束
  
  https://developer.aliyun.com/article/698995
  https://www.jianshu.com/p/917cb4bdaa03
  https://www.jianshu.com/p/044e95223a17