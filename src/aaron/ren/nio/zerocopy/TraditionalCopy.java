package aaron.ren.nio.zerocopy;


import java.io.File;
import java.io.RandomAccessFile;

public class TraditionalCopy {
    public static void main(String[] args) throws Exception {
        File input = new File("D:\\BigData\\JavaSE\\yinzhengjieData\\yinzhengjieJava.rar");
        File tcOutput = new File("D:\\BigData\\JavaSE\\yinzhengjieData\\yinzhengjieJava2.rar");

        traditionalCopy(input, tcOutput);
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
        System.out.println("traditionalCopy ÓÃÊ±Îª:" + (end - start));
    }
}
