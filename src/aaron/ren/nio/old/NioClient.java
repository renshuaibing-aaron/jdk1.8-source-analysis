package aaron.ren.nio.old;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class NioClient {

    private SocketChannel clientSocketChannel;
    private Selector selector;
    private final List<String> responseQueue = new ArrayList<String>();

    private CountDownLatch connected = new CountDownLatch(1);

    public NioClient() throws IOException, InterruptedException {
        // �� Client Socket Channel
        clientSocketChannel = SocketChannel.open();
        // ����Ϊ������
        clientSocketChannel.configureBlocking(false);
        // ���� Selector
        selector = Selector.open();
        // ע�� Server Socket Channel �� Selector
        clientSocketChannel.register(selector, SelectionKey.OP_CONNECT);
        // ���ӷ�����
        clientSocketChannel.connect(new InetSocketAddress(8080));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    handleKeys();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        if (connected.getCount() != 0) {
            connected.await();
        }
        System.out.println("Client �������");
    }

    @SuppressWarnings("Duplicates")
    private void handleKeys() throws IOException {
        while (true) {
            // ͨ�� Selector ѡ�� Channel
            int selectNums = selector.select(30 * 1000L);
            if (selectNums == 0) {
                continue;
            }

            // ������ѡ��� Channel �� SelectionKey ����
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove(); // �Ƴ�����Ҫ����� SelectionKey
                if (!key.isValid()) { // ������Ч�� SelectionKey
                    continue;
                }

                handleKey(key);
            }
        }
    }

    private synchronized void handleKey(SelectionKey key) throws IOException {
        // �������Ӿ���
        if (key.isConnectable()) {
            handleConnectableKey(key);
        }
        // ������
        if (key.isReadable()) {
            handleReadableKey(key);
        }
        // д����
        if (key.isWritable()) {
            handleWritableKey(key);
        }
    }

    private void handleConnectableKey(SelectionKey key) throws IOException {
        // �������
        if (!clientSocketChannel.isConnectionPending()) {
            return;
        }
        clientSocketChannel.finishConnect();
        // log
        System.out.println("�����µ� Channel");
        // ע�� Client Socket Channel �� Selector
        clientSocketChannel.register(selector, SelectionKey.OP_READ, responseQueue);
        // ���Ϊ������
        connected.countDown();
    }

    @SuppressWarnings("Duplicates")
    private void handleReadableKey(SelectionKey key) throws ClosedChannelException {
        // Client Socket Channel
        SocketChannel clientSocketChannel = (SocketChannel) key.channel();
        // ��ȡ����
        ByteBuffer readBuffer = CodecUtil.read(clientSocketChannel);
        // ��ӡ����
        if (readBuffer.position() > 0) { // д��ģʽ�£�
            String content = CodecUtil.newString(readBuffer);
            System.out.println("��ȡ���ݣ�" + content);
        }
    }

    @SuppressWarnings("Duplicates")
    private void handleWritableKey(SelectionKey key) throws ClosedChannelException {
        // Client Socket Channel
        SocketChannel clientSocketChannel = (SocketChannel) key.channel();

        // ������Ӧ����
        List<String> responseQueue = (ArrayList<String>) key.attachment();
        for (String content : responseQueue) {
            // ��ӡ����
            System.out.println("д�����ݣ�" + content);
            // ����
            CodecUtil.write(clientSocketChannel, content);
        }
        responseQueue.clear();

        // ע�� Client Socket Channel �� Selector
        clientSocketChannel.register(selector, SelectionKey.OP_READ, responseQueue);
    }

    public synchronized void send(String content) throws ClosedChannelException {
        // ��ӵ���Ӧ����
        responseQueue.add(content);
        // ��ӡ����
        System.out.println("д�����ݣ�" + content);
        // ע�� Client Socket Channel �� Selector
        clientSocketChannel.register(selector, SelectionKey.OP_WRITE, responseQueue);
        selector.wakeup();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        NioClient client = new NioClient();
        for (int i = 0; i < 30; i++) {
            client.send("nihao: " + i);
            Thread.sleep(1000L);
        }
    }

}