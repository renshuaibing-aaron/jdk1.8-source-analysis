1.��������AQS�Ļ���ԭ��
AQS ȫ���г������ͬ���� ���ǲ������Ļ������

AQS���������� state���� �������߳� �ȴ�����   ͨ����Щ���ĵ��������ʵ�ָ������͸���ͬ�����

2.��Ҫ����Condition���ܵ�ʵ��


3.AQS����Ҫ����state״̬�Ļ�ȡ  ����Ǻ�����Դ �����Ķ�д���Ļ�ȡ ���������state��ǰ16λ�ͺ�16λ���ֲ�ͬ������

4.�˽�AQS����Ҫ��Ա����

    // ͷ��㣬��ֱ�Ӱ������� ��ǰ���������߳� �������������
    private transient volatile Node head;
    
    // ������β�ڵ㣬ÿ���µĽڵ�����������뵽���Ҳ���γ���һ������
    private transient volatile Node tail;
    
    // ���������Ҫ�ģ�����ǰ����״̬��0����û�б�ռ�ã����� 0 �������̳߳��е�ǰ��
    // ���ֵ���Դ��� 1������Ϊ���������룬ÿ�����붼���� 1
    private volatile int state;
    
    // ����ǰ���ж�ռ�����̣߳��ٸ�����Ҫ��ʹ�����ӣ���Ϊ����������
    // reentrantLock.lock()����Ƕ�׵��ö�Σ�����ÿ����������жϵ�ǰ�߳��Ƿ��Ѿ�ӵ������
    // if (currentThread == getExclusiveOwnerThread()) {state++}
    private transient Thread exclusiveOwnerThread; //�̳���AbstractOwnableSynchronizer  ע���������λ�ò���AQS���� ��AOS����
