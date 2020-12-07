1.�ڲ��������� ���� һ�������synchronized  ���ǲ�̫�� ������Ϊ��̫������ ������Ϊ
��ᵼ����Դ�Ĵ��л����� ���ͳ����Ч��
 ����취 �ö�д�� (Ϊʲô����concurrentHash ������ �����ڴ������治ֻ��һ����Դ , ������������Դ��ô���ʣ������ǵ�����)
  �ر���   ��ע�����ĵ��õıȽϳ���
  
2.ReentrantReadWriteLock�ĺ���ԭ�� 
 ���������֪����д������������������һ������ һ��д�� ��ʵ������һ���� ������ͼ ��ʵ������һ��syn Ҳ���Ƕ�д������һ��syn
 ��AQS  
 ��sate�ĸ�16λ������ʾ�������߳��� 
  ��16λ��ʾд���̵߳Ľ������

��Դ�����֪�� ��д�������и� final Sync sync; ������new ReentrantReadWriteLock ʱ����г�ʼ�� Ȼ��
��д��������������(��д����д���õ���sync���������Ҫ ������в���)

3.��д����������
 ```java
class CachedData {
   Object data;
   volatile boolean cacheValid;
   final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

   void processCachedData() {
     rwl.readLock().lock();
     if (!cacheValid) {
       // Must release read lock before acquiring write lock
       rwl.readLock().unlock();
       rwl.writeLock().lock();
       try {
         // Recheck state because another thread might have
         // acquired write lock and changed state before we did.
         if (!cacheValid) {
           data = ...
           cacheValid = true;
         }
         //ע����仰 ˵�ĺ���� �������Ĺ����� ͨ�����ͷ�д��֮ǰ ��ȡ������ȡ��
         // Downgrade by acquiring read lock before releasing write lock
         rwl.readLock().lock();
       } finally {
         rwl.writeLock().unlock(); // Unlock write, still hold read
       }
     }

     try {
       use(data);
     } finally {
       rwl.readLock().unlock();
     }
   }
 }
```

˼���������� ����ΪʲôҪ��ȡ����  ����֤���ݵĿɼ��� ������Ҫ�Ƿ�ֹ ������Ӷ��� �п��ܱ�����߳��޸��� 
Ϊ�˱�֤�̵߳Ŀɼ��� ��volatile �Ƿ���У� ���� ��ʹ����volatile ֻ�Ǳ�֤���ݵĿɼ���   ������Ҫ����һ���� ���м� �������ݲ����޸�


3.�ο�����
https://www.zhihu.com/question/265909728
