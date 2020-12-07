package aaron.ren.proxy.proxyproblmresolve;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKProxy implements InvocationHandler {

    private Object target;


    public  void bind(UserInterface userInterface){
        target = userInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before Method Invoke " + method.getName());
        //注意这个地方 注意啊 这个地方也说明了很多时候AOP失效的问题
        //注意和cglib的比较就会知道什么原因
        Object object = method.invoke(target,args);
        System.out.println("After Method Invoke " + method.getName());
        return object;
    }
}
