package aaron.ren.nio.zerocopy;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class ZeroCopy {
    public static void main(String[] args) throws Exception {
        File input = new File("D:\\BigData\\JavaSE\\yinzhengjieData\\yinzhengjieJava.rar");
        File zcOutput = new File("D:\\BigData\\JavaSE\\yinzhengjieData\\yinzhengjieJava3.rar");

        ZeroCopy(input, zcOutput);
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
        srcFC.transferTo(0, input.length(), destFC);
        long end = System.currentTimeMillis();
        System.out.println("ZeroCopy 用时为:" + (end - start));
    }
}
