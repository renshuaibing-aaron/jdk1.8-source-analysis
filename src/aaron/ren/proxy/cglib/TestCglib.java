package aaron.ren.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;

public class TestCglib {
     //˵�� ����cglib��̬����� ���ǽӿ�Ҳ���� ������Ҫ����jar�ļ�

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