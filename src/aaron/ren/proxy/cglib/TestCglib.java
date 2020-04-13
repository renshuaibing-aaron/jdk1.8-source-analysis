package aaron.ren.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;

public class TestCglib {
     //说明 利用cglib动态代理的 不是接口也可以 但是需要引入jar文件

    /**
     * asm-2.2.3,
     * asm-commons-2.2.3,
     * asm-util-2.2.3,
     * cglib-nodep-2.1_3
     * @param args
     */
    public static void main(String[] args) throws Exception {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:/proxy/cglib");

        UserServiceCglib cglib = new UserServiceCglib();
        UserServiceImpl bookFacedImpl = (UserServiceImpl) cglib.getInstance(new UserServiceImpl());
        bookFacedImpl.addUser();

        bookFacedImpl.cannotproxy();
    }
}