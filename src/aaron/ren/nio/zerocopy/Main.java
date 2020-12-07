package aaron.ren.nio.zerocopy;


import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    private static final String ZIP_FILE ="" ;
    private static final long FILE_SIZE =0 ;
    private static final String JPG_FILE_PATH ="" ;
    private static final String SUFFIX_FILE ="";


    public static void main(String[] args) throws Exception {
        File input = new File("E:\\software\\CentOS-6.9-x86_64-bin-DVD1.iso");
        File tcOutput = new File("E:\\Download\\traditionalCopy.zip");
        File zcOutput = new File("E:\\Download\\zeroCopy.zip");


        traditionalCopy(input, tcOutput);
        ZeroCopy(input, zcOutput);
    }

    public static void traditionalCopy(File input, File output) throws Exception {
        long start = System.currentTimeMillis();
        RandomAccessFile rafRead = new RandomAccessFile(input, "rw");
        RandomAccessFile rafWrite = new RandomAccessFile(output, "rw");
        byte[] buf = new byte[1024];
        int len;
        while ((len = rafRead.read(buf)) != -1) {
            rafWrite.write(buf, 0, len);
        }
        rafRead.close();
        rafWrite.close();
        long end = System.currentTimeMillis();
        System.out.println("traditionalCopy 用时为:" + (end - start));
    }

    public static void ZeroCopy(File input, File output) throws Exception {
        long start = System.currentTimeMillis();
        //文件输出
        FileInputStream fis = new FileInputStream(input);
        //源文件通道
        FileChannel srcFC = fis.getChannel();
        //输出流
        FileOutputStream fos = new FileOutputStream(output);
        //输出文件通道
        FileChannel destFC = fos.getChannel();

        //拷贝文件
        srcFC.transferTo(0, input.length(), destFC);
        long end = System.currentTimeMillis();
        System.out.println("ZeroCopy 用时为:" + (end - start));
    }


    //Version 4 使用Map映射文件
    public static void zipFileMap() {
        //开始时间
        long beginTime = System.currentTimeMillis();
        File zipFile = new File(ZIP_FILE);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
             WritableByteChannel writableByteChannel = Channels.newChannel(zipOut)) {
            for (int i = 0; i < 10; i++) {

                zipOut.putNextEntry(new ZipEntry(i + SUFFIX_FILE));

                //内存中的映射文件DirectByteBuffer
                MappedByteBuffer mappedByteBuffer = new RandomAccessFile(JPG_FILE_PATH, "r").getChannel()
                        .map(FileChannel.MapMode.READ_ONLY, 0, FILE_SIZE);

                writableByteChannel.write(mappedByteBuffer);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
