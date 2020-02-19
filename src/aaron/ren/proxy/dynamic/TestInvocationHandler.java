package aaron.ren.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TestInvocationHandler implements InvocationHandler {

    private Object target;
    public TestInvocationHandler(Object target) {
        this.target=target;
    }

    //InvocationHandler接口的invoke方法（这个方法是一个回调方法）。 被代理类中的方法被调用时，实际上是调用的invoke方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke begin");
        System.out.println("method :"+ method.getName()+" is invoked!");
        method.invoke(target,args);
        System.out.println("invoke end");
        return null;
    }
}
