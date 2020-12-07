package aaron.ren.nio.aio;

/**
 * https://zhuanlan.zhihu.com/p/70273939
 * https://www.jianshu.com/p/2d33ad3e89a6
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AioServer {

    private ExecutorService service;

    private AsynchronousServerSocketChannel serverChannel;

    public ExecutorService getService() {
        return service;
    }

    public AsynchronousServerSocketChannel getServerChannel() {
        return serverChannel;
    }

    public AioServer(int port) {
        init(port);
    }



    private void init(int port) {
        System.out.println("server starting at port "+port+"..");
        // ��ʼ�������̳߳�
        service = Executors.newFixedThreadPool(4);
        try {
            // ��ʼ�� AsyncronousServersocketChannel
            serverChannel = AsynchronousServerSocketChannel.open();
            // �����˿�
            serverChannel.bind(new InetSocketAddress(port));
            // �����ͻ�������,����AIO��ÿ��acceptֻ�ܽ���һ��client��������Ҫ
            // �ڴ����߼����ٴε���accept���ڿ�����һ�εļ���
            // ��������ʽ����
            serverChannel.accept(this, new AioHandler());

            try {
                // �������򣬷�ֹ��GC����
                TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AioServer(8000);
    }
}