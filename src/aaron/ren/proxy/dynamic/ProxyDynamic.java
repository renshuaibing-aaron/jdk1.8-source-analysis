package aaron.ren.proxy.dynamic;

import java.lang.reflect.Proxy;

public class ProxyDynamic {
    public static void main(String[] args) {

        TestInvocationHandler testInvocationHandler = new TestInvocationHandler(new SayImpl());
        Say say = (Say) Proxy.newProxyInstance(SayImpl.class.getClassLoader(), SayImpl.class.getInterfaces(), testInvocationHandler);
        say.sayHello("my dear");
    }
}
