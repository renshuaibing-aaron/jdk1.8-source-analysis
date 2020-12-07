1.等值连接 左连接  右连接    首先是sql的一些关键词信息
group by  order by    limit  distinct  avg count 

2. 

获取语文超过平均分的学生的信息
```sql
select *
from StudentScores where score>(select  AVG(score) as score  from StudentScores where class='语文' group by  Class)  and  class='语文'


```

3.一个表字段有学生id,学科、成绩,选出所有学科成绩都大于60分的
https://blog.csdn.net/qq_33982037/article/details/78998367
//下面这个sql待验证
```sql
select * from stuscroe where id not  in (select distinct  id from stuscroe where score <=60)
```

4.在一张表里面找出重复的数据 以及重复的个数
https://www.cnblogs.com/alsf/p/6224933.html
https://www.cnblogs.com/doudouxiaoye/p/5798623.html
```sql
select DepartmentId,sum(1) as num from Employee group by DepartmentId having sum(1) > 1;
```


获取访问量前三的设备信息
select  distinct  id from  device where  id in (select   id  from  (select  count(*) as count,id  from device  group by id  order by   count desc  limit  0 ,3) as t )
