package aaron.ren.proxy.cglib;

/**
 * 业务类
 *
 * 没有实现接口
 *
 * 如果类是final的，则无法生成代理对象，报错
 *
 * 如果方法是final的，代理无效
 *
 * @author Muscleape
 *
 */
public class UserServiceImpl {


    public UserServiceImpl() {
        System.out.println("======UserServiceImpl 构造函数============");
    }

    public void addUser() {
        System.out.println("addUser...");
        //在自己的类中获取
        editUser();
    }

    public void editUser() {
        System.out.println("editUser...");
    }

    /**
     *
     *这个方法不会被代理 因为这个是final方法
     * 因为cglib的动态代理用的是继承  这个方法不会被继承 然后就会进行 找其父类进行
     * 执行方法即可
     */
    final  public void cannotproxy() {
        System.out.println("cannotproxy...");
    }
}