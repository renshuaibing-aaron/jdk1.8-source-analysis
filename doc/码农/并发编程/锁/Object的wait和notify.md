1.ʵ������
```java
public class WaitNotifyCase {
    public static void main(String[] args) {
        final Object lock = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread A is waiting to get lock");
                synchronized (lock) {
                    try {
                        System.out.println("thread A get lock");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("thread A do wait method");
                        //��ʾ�߳�ִ��lock.wait()����ʱ��������и�lock�����monitor�����wait������synchronized������ִ�У�
                        // ���̺߳���Ȼ�Ѿ�������monitor
                        lock.wait();
                        //wait�����Ὣ��ǰ�̷߳���wait set���ȴ������ѣ�
                        // ������lock�����ϵ�����ͬ����������ζ���߳�A�ͷ��������߳�B��������ִ�м�������
                        System.out.println("wait end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread B is waiting to get lock");
                synchronized (lock) {
                    System.out.println("thread B get lock");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //notify������ѡ��wait set������һ���߳̽��л���
                    lock.notify();
                    //notifyAll�����ỽ��monitor��wait set�������̡߳�
                    lock.notifyAll();
                    //ִ����notify�����������������ѵȴ��̣߳���notify���������һ��sleep����Ϳ��Կ���Ч��������߳�Bִ����notify����֮��sleep 5s�������ʱ���ڣ��߳�B���ɳ���monitor���߳�Aֻ�ܼ����ȴ���
                    System.out.println("thread B do notify method");
                }
            }
        }).start();
    }
}
```




3.wait()��sleep()������
  ����˽����������ĵײ�ܺûش� wait()��object�ķ��� ��ִ��wait����֮ǰ��Ҫ���� Ȼ���ͷ��� �������ĵȴ����� Ҳ����˵���wait()������ʵ�ڲ�(JVM�ڲ��в���)
  sleep()��Thread�ľ�̬���� �ײ���õ��Ǹ�native ���ط��� ��ִ�е�ʱ�� �̻߳�ֹͣ ���ǲ����ͷ���Դ(һЩ����Դ) ��������׽���
  sleep() ֻ���߳�ֹͣ û����������
  

 5.Ӧ����ѭ�����жϵȴ����� 
   ���ڵȴ�״̬���߳̿��ܻ��յ����󾯱���α���ѣ��������ѭ���м��ȴ�����������ͻ���û���������������������˳�
   �������ѭ���м��ȴ�����������ͻ���û���������������������˳�����ˣ���һ���ȴ��߳�����ʱ��������Ϊ��ԭ���ĵȴ�״̬��Ȼ����Ч�ģ�
   ��notify()��������֮��͵ȴ��߳�����֮ǰ���ʱ�������ܻ�ı䡣�������ѭ����ʹ��wait()����Ч�����õ�ԭ��
 ```java
// The standard idiom for using the wait method
synchronized (obj) {
    while (condition does not hold) {
        obj.wait(); // (Releases lock, and reacquires on wakeup)
    }
    ... // Perform action appropriate to condition
}
```


2.
    //�������û�н��� Ϊʲô��Ҫ����
    
   ����wait/notify����֮ǰ��ΪʲôҪ��ȡsynchronized���� �����ʵ��wait�ĵײ�ʵ��ԭ���й� ͨ�����ľ���ʵ�� �ȴ�/���Ѳ���
    �򵥵�˵ syn���������γ�monitor����Ȼ�� �ھ�������ʱ����ʵ ÿ����������������һ����ڶ��к�һ���ȴ�����
    wait() �����ı��ʾ��ǰ�����̷߳Ž��ȴ�����  �ڶ�����ȴ����н��в�����ʱ�������� ����к����� �ͷ���
  �߳�A��ȡ��synchronized����ִ��wait�����������߳�B������ٴλ�ȡ����
     //����һ��ѧ˵���� ֮���Լ�������Ϊ��ֹ��ʧ�������� �������̼߳���
     https://www.cnblogs.com/chen--biao/p/11358016.html
     https://blog.csdn.net/WenWu_Both/article/details/106520799
     
 ����ԭ������� ��ֹ��ʧ���� �������̼߳���  
  

// �߳�A �Ĵ���
while(!condition){ // ����ʹ�� if , ��Ϊ����һЩ��������� ʹ���߳�û���յ� notify ʱҲ���˳��ȴ�״̬
    wait();
}
// do something

// �߳� B �Ĵ���
if(!condition){
    // do something ...
    condition = true;
    notify();
}
���ڿ��ǣ� ���wait() �� notify() �Ĳ���û����Ӧ��ͬ�����ƣ� ��ᷢ���������

���߳�A�� ������ while ѭ����ͨ���� !condition �ж������� ����δִ�� wait ������, CPU ʱ��Ƭ�ľ��� CPU ��ʼִ���߳�B�Ĵ���
���߳�B�� ִ������� condition = true; notify(); �Ĳ����� ��ʱ���߳�A���� wait() ������δ��ִ�У� notify() ����û�в����κ�Ч��
���߳�A��ִ��wait() ������ ����ȴ�״̬�����û�ж���� notify() ������ ���߳̽������� condition = true �������£� �������ڵȴ�״̬�ò���ִ�С�

