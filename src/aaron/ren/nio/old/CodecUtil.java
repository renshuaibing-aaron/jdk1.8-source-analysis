package aaron.ren.nio.old;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class CodecUtil {

    // CodecUtil.java

    public static String newString(ByteBuffer buffer) {
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        System.arraycopy(buffer.array(), buffer.position(), bytes, 0, buffer.remaining());
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


    // CodecUtil.java

    public static void write(SocketChannel channel, String content) {
        // д�� Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            buffer.put(content.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        // д�� Channel
        buffer.flip();
        try {
            // ע�⣬������д�볬�� Channel ���������ޡ�
            channel.write(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // CodecUtil.java

    public static ByteBuffer read(SocketChannel channel) {
        // ע�⣬�����ǲ���Ĵ���
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            int count = channel.read(buffer);
            if (count == -1) {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return buffer;
    }
}
