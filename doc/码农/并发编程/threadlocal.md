2.threadlocal������Ӧ��
��web��Ŀ����  ���Դ��ÿ��������û���Ϣ

2.threadlocal�ĵײ����ݽṹ ��Ҫ�����˽�java�е������õ�֪ʶ
  
3.threadlocal���ڴ�й¶��ԭ�� �Ѿ�  



�Լ������ ��Thread�߳��ڲ��и�ThreadLocalMap ���map�д�ŵ���Entry(ע��������Entry������������)�������õĶ�����
ThreadLocal �ײ�ԭ���� ���̶߳����ȡ�����map  Ȼ������threadlocal ���key�ҵ�entry ,��ôΪʲҪʹ���������أ�
���粻ʹ��������  һ������� map �е�entry�е�key ��ǿ����  ������ǿ���� ,threadlocal Ӧ�ñ��ͷź� ��Ϊthreadlocal����threadlocalMap ��ǿ����
����threadlocal ��������޷��ͷ� ����ڴ�й© (�ܶ಩��д����valueй© �����˾������� ���Ե�)  ������Ҫʹ��������
```java
    static class Entry extends WeakReference<ThreadLocal<?>> {
            /** The value associated with this ThreadLocal. */
            Object value;

            Entry(ThreadLocal<?> k, Object v) {
                super(k);   //���������������
                value = v;
            }
        }
```