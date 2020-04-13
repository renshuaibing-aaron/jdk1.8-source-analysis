package aaron.ren.nio.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * IO Ҳ��Ϊ BIO��Block IO ����ͬ����ͨѶ��ʽ
 * �Ƚϴ�ͳ�ļ�����ʵ�ʿ����л�������Netty������AIO����ϤBIO��NIO��������б仯�Ĺ��̡���Ϊһ��web������Ա��stockͨѶ���Ծ������⡣
 * BIO���������ǣ�������ͬ����
 * BIOͨѶ��ʽ�����������磬�����ٲ��ã�����ʱ���ܳ���ÿ�������ɳ���ִ�в����أ�����ͬ����ȱ�ݡ�
 * BIO�������̣�
 * ��һ����server�˷���������
 * �ڶ�����server�˷�������������client����
 * ��������server�˷������������󣬴����߳�ʵ������
 * @author itdragon
 *
 */
public class ITDragonBIOServer {

    private static final Integer PORT = 8888; // ����������Ķ˿ں�

    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;
        ThreadPoolExecutor executor = null;
        try {
            server = new ServerSocket(PORT); // ServerSocket ���������˿�
            System.out.println("BIO Server ����������.........");
            /*--------------��ͳ�������̴߳���----------------*/
            /*while (true) {
                // �������������������ȴ�Client����
                socket = server.accept();
                System.out.println("server ������ȷ������ : " + socket);
                // ����������ȷ�ϣ�ȷ��Client����󣬴����߳�ִ������  �������Ե����⣬��ÿ����һ�������Ҫ����һ���̣߳���Ȼ�ǲ�����ġ�
                new Thread(new ITDragonBIOServerHandler(socket)).start();
            } */
            /*--------------ͨ���̳߳ش�����߲��������������ѹ����α�첽IO��̣�----------------*/
            executor = new ThreadPoolExecutor(10, 100, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(50));
            while (true) {
                socket = server.accept();  // �������������������ȴ�Client����
                ITDragonBIOServerHandler serverHandler = new ITDragonBIOServerHandler(socket);
                executor.execute(serverHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != socket) {
                    socket.close();
                    socket = null;
                }
                if (null != server) {
                    server.close();
                    server = null;
                    System.out.println("BIO Server �������ر��ˣ�������");
                }
                executor.shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}