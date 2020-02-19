package aaron.ren.atomicinteger;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ԭ����Ĳ���
 */
public class AtomicTest {
    private static AtomicInteger atomicInteger = new AtomicInteger();

    //��ȡ��ǰֵ
    public static void getCurrentValue(){
        System.out.println(atomicInteger.get());//-->0
    }

    //����valueֵ
    public static void setValue(){
        atomicInteger.set(12);//ֱ����12���Ǿ�ֵ
        System.out.println(atomicInteger.get());//-->12
    }

    //���ݷ�������getAndSet��֪����get������󷵻صľ��Ǿ�ֵ�����get�ں󣬾��Ƿ�����ֵ
    public static void getAndSet(){
        System.out.println(atomicInteger.getAndSet(15));//-->12
    }

    public static void getAndIncrement(){
        System.out.println(atomicInteger.getAndIncrement());//-->15
    }

    public static void getAndDecrement(){
        System.out.println(atomicInteger.getAndDecrement());//-->16
    }

    public static void getAndAdd(){
        System.out.println(atomicInteger.getAndAdd(10));//-->15
    }

    public static void incrementAndGet(){
        System.out.println(atomicInteger.incrementAndGet());//-->26
    }

    public static void decrementAndGet(){
        System.out.println(atomicInteger.decrementAndGet());//-->25
    }

    public static void addAndGet(){
        System.out.println(atomicInteger.addAndGet(20));//-->45
    }

    public static void main(String[] args) {
        getCurrentValue();
        setValue();
        //���ؾ�ֵϵ��
        getAndSet();
        getAndIncrement();
        getAndDecrement();
        getAndAdd();
        //������ֵϵ��
        incrementAndGet();
        decrementAndGet();
        addAndGet();


    }
}