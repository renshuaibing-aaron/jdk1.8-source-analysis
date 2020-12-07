package aaron.ren.nio.aio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class AioClient {
    private AsynchronousSocketChannel clientChannel;

    public AioClient(String host, int port) {
        init(host,port);
    }

    private void init(String host, int port) {
        try {
            clientChannel = AsynchronousSocketChannel.open();
            clientChannel.connect(new InetSocketAddress(host,port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doWrite(String line) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(line.getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        clientChannel.write(buffer);
    }

    public void doRead() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            // read() ��һ���첽������ʵ����OSʵ�֣�
            // get()������,�˴�ʹ����������Ϊ����Ҫ�ѽ����ӡ
            // Ҳ����ȥ��get�����Ǿͱ���ʵ�� CompletionHandler
            // ����server�˶�ȡ��������
            clientChannel.read(buffer).get();
            buffer.flip();
            System.out.println("from server: "+new String(buffer.array(),StandardCharsets.UTF_8));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void doDestory() {
        if (null != clientChannel) {
            try {
                clientChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        AioClient client = new AioClient("localhost", 8000);
        try {
            System.out.println("enter your message to server : ");
            Scanner s = new Scanner(System.in);
            String line = s.nextLine();
            client.doWrite(line);
            client.doRead();
        } finally {
            client.doDestory();
        }
    }
}
