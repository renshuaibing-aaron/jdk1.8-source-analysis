package aaron.ren.proxy.cglib;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import net.sf.cglib.core.DefaultGeneratorStrategy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class UserServiceCglib implements MethodInterceptor {

    private Object target;

    public Object getInstance(Object target) throws Exception {
        this.target = target;

        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(this.target.getClass());
        // 设置enhancer的回调对象
        enhancer.setCallback(this);
        // 创建代理对象
        Object object = enhancer.create();

        return object;
    }

    /**
     *
     * @param object  obj表示增强的对象，即实现这个接口类的一个对象
     * @param method  method表示要被拦截的方法
     * @param args  args表示要被拦截方法的参数
     * @param proxy proxy表示要触发父类的方法对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("transtaton...start");
        Object result = proxy.invokeSuper(object, args);
        System.out.println("transtaton...end");
        return result;
    }

}