1.��ҳ��ѯ��sql

select * from orders_history where type=8 order by id limit 10000,10;



2.���� 

GROUP BY


3.

  Table �������ֶ� id, name, sex, age, city��������ƽ���������ĳ��У���˵˵��ν���������дһ����� sql��
  ```sql
select city from Table where sex='��' group by city order by avg(age) desc limit 1;
```
  
4.Mysql 5.1��ɾ���������ݺ�����IDδ����Ĵ�����
//�����Ա���t_userΪ������˵��
//ɾ����t_user�����ݣ����������в�����¼��������������ڴ˱��в������ݣ���id�����1��ʼ�����Ǵ�֮ǰ��id������
DELETE FROM t_user;
//�������ַ�����������ɾ���������ݺ�����ID���㣬����������ݵĲ���
//����һ����turncateɾ����t_user�����ݣ���id���㣬�´β������ݴ�1��ʼ��
TRUNCATE t_user;
//������������������
1��DELETE FROM t_user;
2��ALTER TABLE t_user AUTO_INCREMENT=1;



5.sql��join����Щ����ʲô����
˵˵join
  ������ inner join��ȡ���������ֶ�ƥ���ϵ�ļ�¼
  ������ left join  ��ȡ�������м�¼ ��ʹ�ұ�û�ж�Ӧ��ƥ���¼
  ������ right join ��ȡ�ұ�����м�¼ ��ʹ���û�ж�Ӧ��ƥ���¼

GroupBy
  ���� һ������� ���ǿ������÷���  Ȼ������ڵ��������� ���� ��� ��ƽ��ֵ�ȵ�


6.mysql�����ʵ�ַ�������ȡtop����  �Ѿ�����Ż�
https://blog.csdn.net/weixin_42821133/article/details/106491983


���ݿ���delete��drop������


DB����ѯ��ƽ���ָߵ�ѧ������ sql


����3��a,b,c����֧�ֲ�ѯ��a,b��(b) (b,c)��������ν�


7.���������
```sql
CREATE TABLE Person 
(
LastName varchar(30),
FirstName varchar,
Address varchar,
Age int(3)
)
```

���������
```sql
CREATE DATABASE test_db;
```