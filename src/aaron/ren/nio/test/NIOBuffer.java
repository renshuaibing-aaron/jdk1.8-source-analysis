package aaron.ren.nio.test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOBuffer {

    public static void main(String args[]) {
        try {

            RandomAccessFile file = new RandomAccessFile("d:/tmp/2.txt", "rw");
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(4);
            ByteBuffer bufferDirect = ByteBuffer.allocateDirect(4);

            int bytesRead = channel.read(buffer);
            while (bytesRead != -1) {
                System.out.println("Read " + bytesRead);
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear();
                bytesRead = channel.read(buffer);
            }

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
