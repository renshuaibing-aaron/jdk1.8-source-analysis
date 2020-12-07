package aaron.ren.jvm.unsafedemo;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Test {
    private int value;

    public static void main(String[] args) throws Exception {
        //获取theUnsafe字段
        Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        //私有变量设置访问权限
        unsafeField.setAccessible(true);
        //theUnsafe是静态变量，直接通过Field#get(null)获取
        Unsafe theUnsafe = (Unsafe) unsafeField.get(null);

        //创建Test实例
        Test test = new Test();
        //获取value字段在Test中的偏移量
        long fieldOffset = theUnsafe.objectFieldOffset(Test.class.getDeclaredField("value"));
        //直接操作该内存，设置值
        theUnsafe.putInt(test, fieldOffset, 100);
        //获取该偏移的值
        System.out.println(theUnsafe.getIntVolatile(test, fieldOffset));
    }
}