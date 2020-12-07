package aaron.ren.nio.aio;


import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class AioHandler implements CompletionHandler<AsynchronousSocketChannel, AioServer> {
    @Override
    public void completed(AsynchronousSocketChannel result, AioServer attachment) {
        // ������һ�ε�client���ӡ�������ʽ����
        attachment.getServerChannel().accept(attachment, this);

        // ִ��ҵ���߼�
        doRead(result);
    }

    /**
     * ��ȡclient���͵���Ϣ��ӡ������̨
     *
     * AIO��OS�Ѿ��������������read��IO��������������ֱ���õ��˶�ȡ�Ľ��
     *
     *
     * @param clientChannel ������ڿͻ���ͨ�ŵ� channel
     */
    private void doRead(AsynchronousSocketChannel clientChannel) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // ��client��ȡ����,�����ǵ���clientChannel.read()֮ǰOS���Ѿ������������IO����
        // ����ֻ��Ҫ��һ������������Ŷ�ȡ�����ݼ���
        clientChannel.read(
                buffer,   // ����������ת������
                buffer,   // ���ڴ洢client���͵����ݵĻ�����
                new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {
                        System.out.println("receive client data length��" + attachment.capacity() + " byte");
                        attachment.flip(); // �ƶ� limitλ��
                        // ��ȡclient���͵�����
                        System.out.println("from client : "+new String(attachment.array(), StandardCharsets.UTF_8));

                        // ��clientд������
                        doWrite(clientChannel);
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {

                    }
                }
        );
    }

    private void doWrite(AsynchronousSocketChannel clientChannel) {

        // ��client�������ݣ�clientChannel.write()��һ���첽���ã��÷���ִ�к��֪ͨ
        // OSִ��д��IO����������������
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Scanner s = new Scanner(System.in);
        String line = s.nextLine();
        buffer.put(line.getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        clientChannel.write(buffer);
        // clientChannel.write(buffer).get(); // �����������ֱ��OSд�������
    }

    /**
     * �쳣�����߼�
     *
     * @param exc
     * @param attachment
     */
    @Override
    public void failed(Throwable exc, AioServer attachment) {
        exc.printStackTrace();
    }
}

