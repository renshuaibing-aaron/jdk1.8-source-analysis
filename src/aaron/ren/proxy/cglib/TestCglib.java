package aaron.ren.proxy.cglib;

public class TestCglib {
     //˵�� ����cglib��̬����� ���ǽӿ�Ҳ���� ������Ҫ����jar�ļ�

    /**
     * asm-2.2.3,
     * asm-commons-2.2.3,
     * asm-util-2.2.3,
     * cglib-nodep-2.1_3
     * @param args
     */

    public static void main(String[] args) {
        UserServiceCglib cglib = new UserServiceCglib();
        UserServiceImpl bookFacedImpl = (UserServiceImpl) cglib.getInstance(new UserServiceImpl());
        bookFacedImpl.addUser();
    }
}