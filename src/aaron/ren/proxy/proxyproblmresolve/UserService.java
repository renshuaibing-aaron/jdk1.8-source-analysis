package aaron.ren.proxy.proxyproblmresolve;

public class UserService implements UserInterface {
    @Override
    public void update() {
        System.out.println("userDao.update()");
    }

    @Override
    public void complex(){
        System.out.println("begin complex()");
        //在aop里面 这个this是真实的对象 也就是 在这个方法里面complex不会被代理
        //这里要牢记jdk的动态代理是基于反射实现的
        this.update();
        System.out.println("end complex()");
    }
}
