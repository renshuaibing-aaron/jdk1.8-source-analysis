package aaron.ren.nio.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/**
 * BIO �ͻ���
 * Socket : 		�����˷�������
 * PrintWriter : 	�����˴��ݲ���
 * BufferedReader : �ӷ���˽��ղ���
 * @author itdragon
 *
 */
public class ITDragonBIOClient {

    private static Integer PORT = 8888;
    private static String IP_ADDRESS = "127.0.0.1";

    public static void main(String[] args) {
        // ģ��10���ͻ��˷�������
        for (int i = 0; i < 10; i++) {
            clientReq(i);
        }
    }

    private static void clientReq(int i) {
        Socket socket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            socket = new Socket(IP_ADDRESS, PORT); // Socket �������Ӳ��������ӳɹ���˫��ͨ����������������ͬ������ʽͨ��
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // ��ȡ��������
            writer = new PrintWriter(socket.getOutputStream(), true);
            String []operators = {"+","-","*","/"};
            Random random = new Random(System.currentTimeMillis());
            String expression = random.nextInt(10)+operators[random.nextInt(4)]+(random.nextInt(10)+1);
            writer.println(expression); // ��������˷�������
            System.out.println(i + " �ͻ��˴�ӡ�������� : " + reader.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != reader) {
                    reader.close();
                }
                if (null != socket) {
                    socket.close();
                    socket = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}