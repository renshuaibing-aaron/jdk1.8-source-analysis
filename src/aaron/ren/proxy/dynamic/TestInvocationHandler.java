package aaron.ren.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TestInvocationHandler implements InvocationHandler {

    private Object target;
    public TestInvocationHandler(Object target) {
        this.target=target;
    }

    //InvocationHandler�ӿڵ�invoke���������������һ���ص��������� ���������еķ���������ʱ��ʵ�����ǵ��õ�invoke����
    //spring �Ƚ�ţ�Ƶĵط���ʲô? �����Է�������ƥ��  ������ʵ�����Ǹ�����  Ȼ����д���
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke begin");
        System.out.println("method :"+ method.getName()+" is invoked!");
        method.invoke(target,args);
        System.out.println("invoke end");
        return null;
    }
}
