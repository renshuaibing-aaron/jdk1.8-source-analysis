package aaron.ren.concurrentprogramming.whilenotify;

import java.util.*;

public class EarlyNotify extends Object {
    private List list;

    public EarlyNotify() {
        list = Collections.synchronizedList(new LinkedList());
    }

    public String removeItem() throws InterruptedException {
        print("in removeItem() - entering");

        synchronized ( list ) {
            if ( list.isEmpty() ) {  //������if���ᷢ��Σ��
                print("in removeItem() - about to wait()");
                list.wait();
                print("in removeItem() - done with wait()");
            }

            //ɾ��Ԫ��
            String item = (String) list.remove(0);

            print("in removeItem() - leaving");
            return item;
        }
    }

    public void addItem(String item) {
        print("in addItem() - entering");
        synchronized ( list ) {
            //���Ԫ��
            list.add(item);
            print("in addItem() - just added: '" + item + "'");

            //��Ӻ�֪ͨ�����߳�
            list.notifyAll();
            print("in addItem() - just notified");
        }
        print("in addItem() - leaving");
    }

    private static void print(String msg) {
        String name = Thread.currentThread().getName();
        System.out.println(name + ": " + msg);
    }

    public static void main(String[] args) {
        final EarlyNotify en = new EarlyNotify();

        Runnable runA = new Runnable() {
            @Override
            public void run() {
                try {
                    String item = en.removeItem();
                    print("in run() - returned: '" +
                            item + "'");
                } catch ( InterruptedException ix ) {
                    print("interrupted!");
                } catch ( Exception x ) {
                    print("threw an Exception!!!\n" + x);
                }
            }
        };

        Runnable runB = new Runnable() {
            @Override
            public void run() {
                en.addItem("Hello!");
            }
        };

        try {
            //������һ��ɾ��Ԫ�ص��߳�
            Thread threadA1 = new Thread(runA, "threadA1");
            threadA1.start();

            Thread.sleep(500);

            //�����ڶ���ɾ��Ԫ�ص��߳�
            Thread threadA2 = new Thread(runA, "threadA2");
            threadA2.start();

            Thread.sleep(500);
            //��������Ԫ�ص��߳�
            Thread threadB = new Thread(runB, "threadB");
            threadB.start();

            Thread.sleep(10000); // wait 10 seconds

            threadA1.interrupt();
            threadA2.interrupt();
        } catch ( InterruptedException x ) {}
    }
}


/*
*
threadA1: in removeItem() - entering
threadA1: in removeItem() - about to wait()
threadA2: in removeItem() - entering
threadA2: in removeItem() - about to wait()
threadB: in addItem() - entering
threadB: in addItem() - just added: 'Hello!'
threadB: in addItem() - just notified
threadA2: in removeItem() - done with wait()
threadA2: in removeItem() - leaving
threadA2: in run() - returned: 'Hello!'
threadA1: in removeItem() - done with wait()
threadA1: threw an Exception!!!
java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
threadB: in addItem() - leaving
*
* ��������threadA1��threadA1��removeItem�����е���wait�������Ӷ��ͷ�list�ϵĶ��������ٹ�500ms������threadA2��threadA2����removeItem��������ȡlist�ϵĶ�������Ҳ�����б�Ϊ�գ��Ӷ���wait�����������������ͷ�list�ϵĶ��������ٹ�500ms������threadB��������addItem�����list�ϵĶ�����������list�����һ��Ԫ�أ�ͬʱ��notifyAll֪ͨ�����̡߳�

??? threadA1��threadA2����wait�������أ��ȴ���ȡlist�����ϵĶ�����������ͼ���б���ɾ����ӵ�Ԫ�أ���ͻ�����鷳��ֻ������һ�������ܳɹ�������threadA1��ȡ��list�ϵĶ���������ɾ��Ԫ�سɹ������˳�synchronized�����ʱ��������ͷ�list�ϵĶ���������ʱthreadA2����ȡlist�ϵĶ������������ɾ��list�е�Ԫ�أ�����list�Ѿ�Ϊ���ˣ������׳�IndexOutOfBoundsException��

����취��

��if��Ϊwhile�������ж��Ƿ�Ϊ�գ�Ϊ��ʱ�����ȴ�����

����while���棬�Ƿ�ֹ����waiting�Ķ��󱻱��ԭ������˻��ѷ���������while�����������û�����㣨Ҳ���ܵ�ʱ�����ˣ��������ڱ���̲߳������ֲ������ˣ�������Ҫ�ٴε���wait�������
* */