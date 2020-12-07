package aaron.ren.proxy.proxyproblmresolve;

public class UserService implements UserInterface {
    @Override
    public void update() {
        System.out.println("userDao.update()");
    }

    @Override
    public void complex(){
        System.out.println("begin complex()");
        //��aop���� ���this����ʵ�Ķ��� Ҳ���� �������������complex���ᱻ����
        //����Ҫ�μ�jdk�Ķ�̬�����ǻ��ڷ���ʵ�ֵ�
        this.update();
        System.out.println("end complex()");
    }
}
