1.��ֵ���� ������  ������    ������sql��һЩ�ؼ�����Ϣ
group by  order by    limit  distinct  avg count 

2. 

��ȡ���ĳ���ƽ���ֵ�ѧ������Ϣ
```sql
select *
from StudentScores where score>(select  AVG(score) as score  from StudentScores where class='����' group by  Class)  and  class='����'


```

3.һ�����ֶ���ѧ��id,ѧ�ơ��ɼ�,ѡ������ѧ�Ƴɼ�������60�ֵ�
https://blog.csdn.net/qq_33982037/article/details/78998367
//�������sql����֤
```sql
select * from stuscroe where id not  in (select distinct  id from stuscroe where score <=60)
```

4.��һ�ű������ҳ��ظ������� �Լ��ظ��ĸ���
https://www.cnblogs.com/alsf/p/6224933.html
https://www.cnblogs.com/doudouxiaoye/p/5798623.html
```sql
select DepartmentId,sum(1) as num from Employee group by DepartmentId having sum(1) > 1;
```


��ȡ������ǰ�����豸��Ϣ
select  distinct  id from  device where  id in (select   id  from  (select  count(*) as count,id  from device  group by id  order by   count desc  limit  0 ,3) as t )
