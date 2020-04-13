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

        // ͨ��CGLIB��̬�����ȡ�������Ĺ���
        Enhancer enhancer = new Enhancer();
        // ����enhancer����ĸ���
        enhancer.setSuperclass(this.target.getClass());
        // ����enhancer�Ļص�����
        enhancer.setCallback(this);
        // �����������
        Object object = enhancer.create();

        return object;
    }

    /**
     *
     * @param object  obj��ʾ��ǿ�Ķ��󣬼�ʵ������ӿ����һ������
     * @param method  method��ʾҪ�����صķ���
     * @param args  args��ʾҪ�����ط����Ĳ���
     * @param proxy proxy��ʾҪ��������ķ�������
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