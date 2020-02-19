package aaron.ren.nio.old;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NioServer {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public NioServer() throws IOException {
        // �� Server Socket Channel
        serverSocketChannel = ServerSocketChannel.open();
        // ����Ϊ������
        serverSocketChannel.configureBlocking(false);
        // �� Server port
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        // ���� Selector
        selector = Selector.open();
        // ע�� Server Socket Channel �� Selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server �������");

        handleKeys();
    }

    private void handleKeys() throws IOException {
        while (true) {
            // ͨ�� Selector ѡ�� Channel
            int selectNums = selector.select(30 * 1000L);
            if (selectNums == 0) {
                continue;
            }
            System.out.println("ѡ�� Channel ������" + selectNums);

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

    private void handleKey(SelectionKey key) throws IOException {
        // �������Ӿ���
        if (key.isAcceptable()) {
            handleAcceptableKey(key);
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

    private void handleAcceptableKey(SelectionKey key) throws IOException {
        // ���� Client Socket Channel
        SocketChannel clientSocketChannel = ((ServerSocketChannel) key.channel()).accept();
        // ����Ϊ������
        clientSocketChannel.configureBlocking(false);
        // log
        System.out.println("�����µ� Channel");
        // ע�� Client Socket Channel �� Selector
        clientSocketChannel.register(selector, SelectionKey.OP_READ, new ArrayList<String>());
    }

    private void handleReadableKey(SelectionKey key) throws IOException {
        // Client Socket Channel
        SocketChannel clientSocketChannel = (SocketChannel) key.channel();
        // ��ȡ����
        ByteBuffer readBuffer = CodecUtil.read(clientSocketChannel);
        // ���������Ѿ��Ͽ������
        if (readBuffer == null) {
            System.out.println("�Ͽ� Channel");
            clientSocketChannel.register(selector, 0);
            return;
        }
        // ��ӡ����
        if (readBuffer.position() > 0) {
            String content = CodecUtil.newString(readBuffer);
            System.out.println("��ȡ���ݣ�" + content);

            // ��ӵ���Ӧ����
            List<String> responseQueue = (ArrayList<String>) key.attachment();
            responseQueue.add("��Ӧ��" + content);
            // ע�� Client Socket Channel �� Selector
            clientSocketChannel.register(selector, SelectionKey.OP_WRITE, key.attachment());
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

    public static void main(String[] args) throws IOException {
        NioServer server = new NioServer();
    }
}