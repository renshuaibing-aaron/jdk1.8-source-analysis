package aaron.ren.proxy.dynamic;

import java.lang.reflect.Proxy;

public class ProxyDynamic {
    public static void main(String[] args) {


        JdkProxySourceClass.saveProxyFile();

        TestInvocationHandler testInvocationHandler = new TestInvocationHandler(new SayImpl());
       Say say = (Say) Proxy.newProxyInstance(SayImpl.class.getClassLoader(), SayImpl.class.getInterfaces(), testInvocationHandler);
        System.out.println("=======dynamic proxy=========="+say);

        say.sayHello("my dear");

       // say.write();
    }

    /*
    * 构造函数，将代理的对象传入。
      invoke方法，此方法中实现了 AOP增强的所有逻辑。
      getProxy方法，此方法千篇一律，但是必不可少。
    *
    * */
}
