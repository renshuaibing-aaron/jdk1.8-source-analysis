1.ReentrantLock  ������ ���жϵ���

2.��Ҫ����ʵ��
https://www.jianshu.com/p/1014fdd375cf  
Condition���ReentrantLock����ʵ��synchronized��wait��notify���ƵĹ��ܡ����ҹ��ܱ�synchronized����ǿ���ܹ�ʵ���жϡ���ʱ



3.��һ��condition�ĵײ�ԭ��
  �������к��������еĽڵ㣬���� Node ��ʵ������Ϊ�������еĽڵ�����Ҫת�Ƶ�����������ȥ�ģ�
     ����֪��һ�� ReentrantLock ʵ������ͨ����ε��� newCondition() ��������� Condition ʵ����
  �����Ӧ condition1 �� condition2��ע�⣬ConditionObject ֻ���������� firstWaiter �� lastWaiter��
     ÿ�� condition ��һ���������������У����߳� 1 ���� condition1.await() �������ɽ���ǰ�߳� 1 ��װ�� Node ����뵽���������У�
     Ȼ���������������������ִ�У�����������һ����������
  ����condition1.signal() ����һ�λ��ѣ���ʱ���ѵ��Ƕ�ͷ���Ὣcondition1 ��Ӧ���������е� 
    firstWaiter����ͷ�� �Ƶ��������еĶ�β���ȴ���ȡ������ȡ���� await �������ܷ��أ���������ִ�С�