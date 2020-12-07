package aaron.ren.proxy.proxyproblmresolve;

import aaron.ren.proxy.dynamic.JdkProxySourceClass;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {

        JdkProxySourceClass.saveProxyFile();
        UserService userService = new UserService();
        JDKProxy jdkProxy = new JDKProxy();
        jdkProxy.bind(userService);
        UserInterface userInterface =  (UserInterface)Proxy.newProxyInstance(userService.getClass().getClassLoader(),userService.getClass().getInterfaces(),jdkProxy);
        userInterface.complex();
    }
}