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
        //�ļ����
        FileInputStream fis = new FileInputStream(input);
        //Դ�ļ�ͨ��
        FileChannel srcFC = fis.getChannel();
        //�����
        FileOutputStream fos = new FileOutputStream(output);
        //����ļ�ͨ��
        FileChannel destFC = fos.getChannel();
        srcFC.transferTo(0, input.length(), destFC);
        long end = System.currentTimeMillis();
        System.out.println("ZeroCopy ��ʱΪ:" + (end - start));
    }
}
