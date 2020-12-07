package aaron.ren.jvm.unsafedemo;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Test {
    private int value;

    public static void main(String[] args) throws Exception {
        //��ȡtheUnsafe�ֶ�
        Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        //˽�б������÷���Ȩ��
        unsafeField.setAccessible(true);
        //theUnsafe�Ǿ�̬������ֱ��ͨ��Field#get(null)��ȡ
        Unsafe theUnsafe = (Unsafe) unsafeField.get(null);

        //����Testʵ��
        Test test = new Test();
        //��ȡvalue�ֶ���Test�е�ƫ����
        long fieldOffset = theUnsafe.objectFieldOffset(Test.class.getDeclaredField("value"));
        //ֱ�Ӳ������ڴ棬����ֵ
        theUnsafe.putInt(test, fieldOffset, 100);
        //��ȡ��ƫ�Ƶ�ֵ
        System.out.println(theUnsafe.getIntVolatile(test, fieldOffset));
    }
}