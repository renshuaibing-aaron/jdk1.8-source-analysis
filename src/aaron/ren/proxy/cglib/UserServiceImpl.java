package aaron.ren.proxy.cglib;

/**
 * ҵ����
 *
 * û��ʵ�ֽӿ�
 *
 * �������final�ģ����޷����ɴ�����󣬱���
 *
 * ���������final�ģ�������Ч
 *
 * @author Muscleape
 *
 */
public class UserServiceImpl {


    public UserServiceImpl() {
        System.out.println("======UserServiceImpl ���캯��============");
    }

    public void addUser() {
        System.out.println("addUser...");
        //���Լ������л�ȡ
        editUser();
    }

    public void editUser() {
        System.out.println("editUser...");
    }

    /**
     *
     *����������ᱻ���� ��Ϊ�����final����
     * ��Ϊcglib�Ķ�̬�����õ��Ǽ̳�  ����������ᱻ�̳� Ȼ��ͻ���� ���丸�����
     * ִ�з�������
     */
    final  public void cannotproxy() {
        System.out.println("cannotproxy...");
    }
}