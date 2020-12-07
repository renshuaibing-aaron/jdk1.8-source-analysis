package aaron.ren.jvm.weakreference;


import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Client2 class
 *
 * @author BrightLoong
 * @date 2018/5/27
 */
public class Client2 {
    public static void main(String[] args) {
        ReferenceQueue<Apple> appleReferenceQueue = new ReferenceQueue<>();
        WeakReference<Apple> appleWeakReference = new WeakReference<Apple>(new Apple("��ƻ��"), appleReferenceQueue);
        WeakReference<Apple> appleWeakReference2 = new WeakReference<Apple>(new Apple("��ƻ��"), appleReferenceQueue);

        System.out.println("=====gc����ǰ=====");
        Reference<? extends Apple> reference = null;
        while ((reference = appleReferenceQueue.poll()) != null ) {
            //�����������Ϊû�л��ձ������õĶ��󣬲�������������
            System.out.println(reference);
        }
        System.out.println(appleWeakReference);
        System.out.println(appleWeakReference2);
        System.out.println(appleWeakReference.get());
        System.out.println(appleWeakReference2.get());

        System.out.println("=====����gc=====");
        System.gc();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=====gc���ú�=====");

        //�����������Ϊnull,��ʾ���󱻻�����
        System.out.println(appleWeakReference.get());
        System.out.println(appleWeakReference2.get());

        //�����������Ҿ��������appleWeakReference��appleWeakReference2���ٴ�֤�����󱻻�����
        Reference<? extends Apple> reference2 = null;
        while ((reference2 = appleReferenceQueue.poll()) != null ) {
            //���ʹ�ü̳еķ�ʽ�Ϳ��԰���������Ϣ��
            System.out.println("appleReferenceQueue�У�" + reference2);
        }
    }
}
