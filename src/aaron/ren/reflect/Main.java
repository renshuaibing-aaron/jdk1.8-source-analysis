package aaron.ren.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        //1、通过对象调用 getClass() 方法来获取,通常应用在：比如你传过来一个 Object
        //  类型的对象，而我不知道你具体是什么类，用这种方法
        Person person1 = new Person();
        Class c1 = person1.getClass();

        //2、直接通过 类名.class 的方式得到,该方法最为安全可靠，程序性能更高
        //  这说明任何一个类都有一个隐含的静态成员变量 class
        Class c2 = Person.class;

        //3、通过 Class 对象的 forName() 静态方法来获取，用的最多，
        //   但可能抛出 ClassNotFoundException 异常
        Class c3 = Class.forName("aaron.ren.reflect.Person");


        //---------------------------反射具体方法-------------------------------------------


        String className = c2.getName();
        System.out.println(className);//输出com.qf.cdxt.Person

        //获得类的public类型的属性。
        Field[] fields = c2.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());//age
        }

        //获得类的所有属性。包括私有的
        Field[] allFields = c2.getDeclaredFields();
        for (Field field : allFields) {
            System.out.println(field.getName());//name    age
        }

        //获得类的public类型的方法。这里包括 Object 类的一些方法
        Method[] methods = c2.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());//play waid equls toString hashCode等
        }

        //获得类的所有方法。
        Method[] allMethods = c2.getDeclaredMethods();
        for (Method method : allMethods) {
            System.out.println(method.getName());//play eat
        }

        //获得指定的属性
        Field f1 = c2.getField("age");
        System.out.println(f1);
        //获得指定的私有属性
        Field f2 = c2.getDeclaredField("name");
        //启用和禁用访问安全检查的开关，值为 true，则表示反射的对象在使用时应该取消 java 语言的访问检查；反之不取消
        f2.setAccessible(true);
        System.out.println(f2);

        //创建这个类的一个对象
        Object p2 = c2.newInstance();
        //将 p2 对象的  f2 属性赋值为 Fy，f2 属性即为 私有属性 name
        f2.set(p2, "Fy");
        //使用反射机制可以打破封装性，导致了java对象的属性不安全。
        System.out.println(f2.get(p2)); //Fy

        //获取构造方法
        Constructor[] constructors = c2.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor.toString());//public com.ys.reflex.Person()
        }


    }

}
