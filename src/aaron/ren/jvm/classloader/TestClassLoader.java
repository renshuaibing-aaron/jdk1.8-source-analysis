package aaron.ren.jvm.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        HClassLoader myClassLoader = new HClassLoader("e:/temp/a");
        Class clazz = myClassLoader.loadClass("com.ha.Car");
        Object o = clazz.newInstance();
        Method print = clazz.getDeclaredMethod("print", null);
        print.invoke(o, null);
    }

}
