1.Hystrix������
��·������  ���Լ�����·�Ľ����� ���ж�·��������
Fallback(ʧ�ܻ���)
��Դ�������  ��Ҫ�����ַ�ʽ �̳߳صĸ��� �ź����ĸ��� ��������
ִ��ģ��  ͬ��ִ�� �첽ִ�� reactiveģʽִ�� observe��toObservable
��άƽ̨ ������Dasgboard������Turbine
����
����ϲ� HystrixCollapser


2.Դ��https://github.com/Netflix/Hystrix.git


3.��ϸ����
https://www.cnblogs.com/cjsblog/p/9395584.html


Hystrix��Ҫ��4�ֵ��÷�ʽ��
toObservable() ���� ��δ�����ģ�ֻ�Ƿ���һ��Observable ��
observe() ���� ������ #toObservable() ���������� Observable ע�� rx.subjects.ReplaySubject �����ġ�
queue() ���� ������ #toObservable() �����Ļ����ϣ����ã�Observable#toBlocking() �� BlockingObservable#toFuture() ���� Future ����
execute() ���� ������ #queue() �����Ļ����ϣ����� Future#get() ������ͬ������ #run() ��ִ�н����
��Ҫ��ִ���߼���
1.ÿ�ε��ô���һ���µ�HystrixCommand,���������÷�װ��run()������.

2.ִ��execute()/queue��ͬ�����첽����.

3.�ж��۶���(circuit-breaker)�Ƿ��,�������������8,���н�������,����رս��벽��.

4.�ж��̳߳�/����/�ź����Ƿ�����������������뽵������8,���������������.

5.����HystrixCommand��run����.���������߼�

�����߼����ó�ʱ,���벽��8.

6.�ж��߼��Ƿ���óɹ������سɹ����ý�������ó������벽��8.

7.�����۶���״̬,���е�����״̬(�ɹ�, ʧ��, �ܾ�,��ʱ)�ϱ����۶���������ͳ�ƴӶ��ж��۶���״̬.

8.getFallback()�����߼��������������������getFallback���ã�

run()�����׳���HystrixBadRequestException�쳣��
run()�������ó�ʱ
�۶����������ص���
�̳߳�/����/�ź����Ƿ�����
û��ʵ��getFallback��Command��ֱ���׳��쳣��fallback�����߼����óɹ�ֱ�ӷ��أ������߼�����ʧ���׳��쳣.
