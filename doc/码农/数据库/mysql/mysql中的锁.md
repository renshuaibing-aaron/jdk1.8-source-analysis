���ݿ��ֵĹ������������� ��Java���ֽж�����д�� ��ʵ���Ǳ����� ���Ǳ�������Ҫ�����ݱ�������ʵ��
�ֹ��� ��ָ�����ݿ��������һ���汾����ʵ��

���Ƕ�֪��innoDB�������õ����� ��ײ�ʵ��ģʽ�Ǽ���������
���� select * from table where id=1 for update  �ؼ���for update ���Ը�����������ɼ���  ���id���������� ��ʹ�ñ���
Innodb �����Ĳ���Ϊ next-key ������ record lock + gap lock ����ͨ���� index �ϼ� lock ʵ�ֵġ�
��� index Ϊ unique index ���򽵼�Ϊ record lock ������
�������ͨ index ����Ϊ next-key lock 
���û�� index ����ֱ����סȫ��������

MyISAM ֱ��ʹ�ñ���



mysql�е�
����  ���������
����  �����������
ҳ��  ���������
 
������ �ֹ���  
˵�� ���������ֹ�������� ���������ֹ�������� ����������ҳ

��ͬ�Ĵ洢���� ��Ӧ��������

ʲô�����»��������  ��ô���ƣ�
https://mp.weixin.qq.com/s?__biz=MzU2Njg3OTU1Mg==&mid=2247483909&idx=1&sn=57ab448ea1b95bc32b84bffa1d38f8c7&chksm=fca4f62acbd37f3c129f783a98919a4d71c8a943a27a06a0d2667e41aac06ec7cab463bc4f71&scene=27#wechat_redirect


ʲô�����»�������� ����ΪMyISAMֻ֧�ֱ��� �����������һ������¶�������InnoDB�洢��������� 
һ�����������Session�ļ���˳��һ�µ��µ�

ʲô�ǹ����� ʲô��������

��ִ��select��ʱ����ô����������˵������ʱ����ʲôʱ��
  �����ݿ����ɾ�Ĳ�����ֲ����� insert delete update���ǻ�������� ��selectֻ����ʽ�����Ż����
  select ����Ĳ�ѯ �����κ���
  select ... lock in share mode �ӹ�����
  select ... for update  ��������





































