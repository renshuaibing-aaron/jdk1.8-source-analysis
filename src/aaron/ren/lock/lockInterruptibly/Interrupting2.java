package aaron.ren.lock.lockInterruptibly;

import java.util.concurrent.TimeUnit;

public class Interrupting2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Blocked2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing t.interrupt()");
        t.interrupt();
    }
}
/*

��ӡ�����

        main
        Waiting for f() in BlockedMutex
        Issuing t.interrupt()
        Interrupted from lock acquisition in f()
        Broken out of blocked call
        Thread-0

        ��֪��?Thread t = new Thread(new Blocked2())�е�new Blocked2()��lock�����߳������У��Ҳ��ͷţ�
        ���̵߳���blocked.f()��������������mian�߳�����1s���ж����̣߳��Ͳ��������ϴ�ӡЧ����
        ˵����ReentrantLock�ǿ��жϵģ������ڹؼ������Ĳ����ж����ʡ�
*/
