package aaron.ren.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class UserServiceCglib implements MethodInterceptor {

    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // ���ûص�����
        enhancer.setCallback(this);
        // �����������
        return enhancer.create();
    }

    /**
     * ʵ��MethodInterceptor�ӿ�����д�ķ���
     *
     * �ص�����
     */
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("����ʼ������start");
        Object result = proxy.invokeSuper(object, args);
        System.out.println("�������������end");
        return result;
    }

}