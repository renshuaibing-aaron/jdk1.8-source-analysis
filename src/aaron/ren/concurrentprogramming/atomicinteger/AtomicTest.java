package aaron.ren.concurrentprogramming.atomicinteger;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类的测试
 */
public class AtomicTest {
    private static AtomicInteger atomicInteger = new AtomicInteger();

    //获取当前值
    public static void getCurrentValue(){
        System.out.println(atomicInteger.get());//-->0
    }

    //设置value值
    public static void setValue(){
        atomicInteger.set(12);//直接用12覆盖旧值
        System.out.println(atomicInteger.get());//-->12
    }

    //根据方法名称getAndSet就知道先get，则最后返回的就是旧值，如果get在后，就是返回新值
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
        //返回旧值系列
        getAndSet();
        getAndIncrement();
        getAndDecrement();
        getAndAdd();
        //返回新值系列
        incrementAndGet();
        decrementAndGet();
        addAndGet();


    }
}