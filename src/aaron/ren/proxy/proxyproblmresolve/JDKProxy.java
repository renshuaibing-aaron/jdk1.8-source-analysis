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
        //ע������ط� ע�Ⱑ ����ط�Ҳ˵���˺ܶ�ʱ��AOPʧЧ������
        //ע���cglib�ıȽϾͻ�֪��ʲôԭ��
        Object object = method.invoke(target,args);
        System.out.println("After Method Invoke " + method.getName());
        return object;
    }
}
