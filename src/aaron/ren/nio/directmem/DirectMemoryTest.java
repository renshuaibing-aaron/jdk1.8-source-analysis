package aaron.ren.nio.directmem;

import java.nio.ByteBuffer;

/**
 * ֱ���ڴ� ��  ���ڴ�ıȽ�
 */
public class DirectMemoryTest {


    public static void main(String[] args) {
        allocateCompare();   //����Ƚ�
        operateCompare();    //��д�Ƚ�
    }

    /**
     * ֱ���ڴ� �� ���ڴ�� ����ռ�Ƚ�
     *
     * ���ۣ� ������������ʱ��ֱ���ڴ���ȷ�ֱ���ڵ����룬�к����ص���������
     *
     */
    public static void allocateCompare(){
        int time = 10000000;    //��������


        long st = System.currentTimeMillis();
        for (int i = 0; i < time; i++) {

            //ByteBuffer.allocate(int capacity)   ����һ���µ��ֽڻ�������
            ByteBuffer buffer = ByteBuffer.allocate(2);      //��ֱ���ڴ��������
        }
        long et = System.currentTimeMillis();

        System.out.println("�ڽ���"+time+"�η������ʱ�����ڴ� �����ʱ:" + (et-st) +"ms" );

        long st_heap = System.currentTimeMillis();
        for (int i = 0; i < time; i++) {
            //ByteBuffer.allocateDirect(int capacity) �����µ�ֱ���ֽڻ�������
            ByteBuffer buffer = ByteBuffer.allocateDirect(2); //ֱ���ڴ��������
        }
        long et_direct = System.currentTimeMillis();

        System.out.println("�ڽ���"+time+"�η������ʱ��ֱ���ڴ� �����ʱ:" + (et_direct-st_heap) +"ms" );

    }

    /**
     * ֱ���ڴ� �� ���ڴ�� ��д���ܱȽ�
     *
     * ���ۣ�ֱ���ڴ���ֱ�ӵ�IO �����ϣ���Ƶ���Ķ�дʱ ������������������
     *
     */
    public static void operateCompare(){
        int time = 1000000000;

        ByteBuffer buffer = ByteBuffer.allocate(2*time);
        long st = System.currentTimeMillis();
        for (int i = 0; i < time; i++) {

            //  putChar(char value) ����д�� char ֵ����� put ����
            buffer.putChar('a');
        }
        buffer.flip();
        for (int i = 0; i < time; i++) {
            buffer.getChar();
        }
        long et = System.currentTimeMillis();

        System.out.println("�ڽ���"+time+"�ζ�д����ʱ����ֱ���ڴ��д��ʱ��" + (et-st) +"ms");

        ByteBuffer buffer_d = ByteBuffer.allocateDirect(2*time);
        long st_direct = System.currentTimeMillis();
        for (int i = 0; i < time; i++) {

            //  putChar(char value) ����д�� char ֵ����� put ����
            buffer_d.putChar('a');
        }
        buffer_d.flip();
        for (int i = 0; i < time; i++) {
            buffer_d.getChar();
        }
        long et_direct = System.currentTimeMillis();

        System.out.println("�ڽ���"+time+"�ζ�д����ʱ��ֱ���ڴ��д��ʱ:" + (et_direct - st_direct) +"ms");
    }
}
