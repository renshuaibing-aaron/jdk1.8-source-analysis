package aaron.ren.proxy.dynamic;

import java.lang.reflect.Proxy;

public class ProxyDynamic {
    public static void main(String[] args) {


        JdkProxySourceClass.saveProxyFile();

        TestInvocationHandler testInvocationHandler = new TestInvocationHandler(new SayImpl());
       Say say = (Say) Proxy.newProxyInstance(SayImpl.class.getClassLoader(), SayImpl.class.getInterfaces(), testInvocationHandler);
        System.out.println("================="+say);

        say.sayHello("my dear");
    }

    /*
    * ���캯����������Ķ����롣
      invoke�������˷�����ʵ���� AOP��ǿ�������߼���
      getProxy�������˷���ǧƪһ�ɣ����Ǳز����١�
    *
    * */
}
