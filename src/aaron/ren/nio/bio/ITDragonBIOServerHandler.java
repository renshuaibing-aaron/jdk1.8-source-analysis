package aaron.ren.nio.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * server ��������
 * @author itdragon
 *
 */
public class ITDragonBIOServerHandler implements Runnable{

    private Socket socket;

    public ITDragonBIOServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            writer = new PrintWriter(this.socket.getOutputStream(), true);
            //        System.out.println("��ӡ�ͻ��˴��������� : " + reader.readLine());  �������д��룬�ᵼ�³����޷����� ��ϸ��
            String body = null;
            while (true) {
                body = reader.readLine(); // ���ͻ����õ��� writer.print() ��ֵ����readerLine() �ǲ��ܻ�ȡֵ��ϸ��
                if (null == body) {
                    break;
                }
                System.out.println("server����˽��ղ��� : " + body);
                writer.println(body + " = " + CalculatorUtil.cal(body).toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer) {
                writer.close();
            }
            try {
                if (null != reader) {
                    reader.close();
                }
                if (null != this.socket) {
                    this.socket.close();
                    this.socket = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}