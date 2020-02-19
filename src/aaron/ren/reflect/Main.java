package aaron.ren.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        //1��ͨ��������� getClass() ��������ȡ,ͨ��Ӧ���ڣ������㴫����һ�� Object
        //  ���͵Ķ��󣬶��Ҳ�֪���������ʲô�࣬�����ַ���
        Person person1 = new Person();
        Class c1 = person1.getClass();

        //2��ֱ��ͨ�� ����.class �ķ�ʽ�õ�,�÷�����Ϊ��ȫ�ɿ����������ܸ���
        //  ��˵���κ�һ���඼��һ�������ľ�̬��Ա���� class
        Class c2 = Person.class;

        //3��ͨ�� Class ����� forName() ��̬��������ȡ���õ���࣬
        //   �������׳� ClassNotFoundException �쳣
        Class c3 = Class.forName("aaron.ren.reflect.Person");


        //---------------------------������巽��-------------------------------------------


        String className = c2.getName();
        System.out.println(className);//���com.qf.cdxt.Person

        //������public���͵����ԡ�
        Field[] fields = c2.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());//age
        }

        //�������������ԡ�����˽�е�
        Field[] allFields = c2.getDeclaredFields();
        for (Field field : allFields) {
            System.out.println(field.getName());//name    age
        }

        //������public���͵ķ������������ Object ���һЩ����
        Method[] methods = c2.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());//play waid equls toString hashCode��
        }

        //���������з�����
        Method[] allMethods = c2.getDeclaredMethods();
        for (Method method : allMethods) {
            System.out.println(method.getName());//play eat
        }

        //���ָ��������
        Field f1 = c2.getField("age");
        System.out.println(f1);
        //���ָ����˽������
        Field f2 = c2.getDeclaredField("name");
        //���úͽ��÷��ʰ�ȫ���Ŀ��أ�ֵΪ true�����ʾ����Ķ�����ʹ��ʱӦ��ȡ�� java ���Եķ��ʼ�飻��֮��ȡ��
        f2.setAccessible(true);
        System.out.println(f2);

        //����������һ������
        Object p2 = c2.newInstance();
        //�� p2 �����  f2 ���Ը�ֵΪ Fy��f2 ���Լ�Ϊ ˽������ name
        f2.set(p2, "Fy");
        //ʹ�÷�����ƿ��Դ��Ʒ�װ�ԣ�������java��������Բ���ȫ��
        System.out.println(f2.get(p2)); //Fy

        //��ȡ���췽��
        Constructor[] constructors = c2.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor.toString());//public com.ys.reflex.Person()
        }


    }

}
