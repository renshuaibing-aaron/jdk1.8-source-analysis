package aaron.ren.consistenthash;

/*
* 一致性hash算法的实现 为什么要用treemap来实现
*
* 其实一直性hash算法的本质是 缓存和对象采用相同的hash算法 在0-2的32次方里面进行
*
* 并且保证hash结果的平衡性  增加虚拟节点
* 增加虚拟节点的实现 是对一个服务节点计算多个hash，然后放到圈子上 解决数据倾斜问题  解决负载均衡问题
*
* 在redis集群里面 redis是采取的不是这个算法
*
* */