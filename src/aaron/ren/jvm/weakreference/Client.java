package aaron.ren.jvm.weakreference;

/**
 *https://www.jianshu.com/p/964fbc30151a
 */

public class Client {
    public static void main(String[] args) {

        //todo �ر�ע����� Apple����ֻ��һ�������� ����salad û��ǿ���� ������� �����÷��ڶ�����
        //ע���Threadlocal�Ľ�����
        // static class Entry extends WeakReference<ThreadLocal<?>>
        Salad salad = new Salad(new Apple("�츻ʿ"));
        //ͨ��WeakReference��get()������ȡApple
        System.out.println("Apple:" + salad.get());
        System.gc();
        try {
            //����һ�£������е�ʱ��������������-XX:+PrintGCDetails�����gc��Ϣ��ȷ��gc�����ˡ�
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //���Ϊ�գ�����������
        if (salad.get() == null) {
            System.out.println("clear Apple��");
        }
    }
}
