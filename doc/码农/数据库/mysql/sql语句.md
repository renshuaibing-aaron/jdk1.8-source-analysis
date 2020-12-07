1.分页查询的sql

select * from orders_history where type=8 order by id limit 10000,10;



2.分组 

GROUP BY


3.

  Table 表中有字段 id, name, sex, age, city，求男性平均年龄最大的城市？先说说如何建立索引？写一下这个 sql。
  ```sql
select city from Table where sex='男' group by city order by avg(age) desc limit 1;
```
  
4.Mysql 5.1中删除表中数据后自增ID未归零的处理方法
//以下以表名t_user为例进行说明
//删除表t_user中数据，但保留了行操作记录，即后续如果再在此表中插入数据，则id不会从1开始，而是从之前的id往后排
DELETE FROM t_user;
//以下两种方法均可以让删除表中数据后自增ID归零，方便后续数据的插入
//方法一：用turncate删除表t_user中数据，且id归零，下次插入数据从1开始；
TRUNCATE t_user;
//方法二：分两步进行
1）DELETE FROM t_user;
2）ALTER TABLE t_user AUTO_INCREMENT=1;



5.sql的join有哪些，有什么区别？
说说join
  内连接 inner join获取两个表中字段匹配关系的记录
  左连接 left join  获取左表的所有记录 即使右表没有对应的匹配记录
  右连接 right join 获取右表的所有记录 即使左表没有对应的匹配记录

GroupBy
  分组 一般情况下 我们可以利用分组  然后对组内的数据求函数 比如 求和 求平均值等等


6.mysql是如何实现分组排序取top案例  已经如何优化
https://blog.csdn.net/weixin_42821133/article/details/106491983


数据库中delete和drop的区别


DB语句查询比平均分高的学生总数 sql


表有3列a,b,c，需支持查询（a,b）(b) (b,c)，索引如何建


7.创建表语句
```sql
CREATE TABLE Person 
(
LastName varchar(30),
FirstName varchar,
Address varchar,
Age int(3)
)
```

创建库语句
```sql
CREATE DATABASE test_db;
```