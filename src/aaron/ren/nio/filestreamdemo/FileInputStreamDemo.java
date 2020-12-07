package aaron.ren.nio.filestreamdemo;

import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * https://blog.csdn.net/ai_bao_zi/article/details/81097898
 * https://blog.csdn.net/ai_bao_zi/article/details/81133476
 */
public class FileInputStreamDemo {

    public static void main(String[] args) throws IOException {
        readdemo();

        StringWriter writer=new StringWriter();
        FileInputStream  fileInputStream=new FileInputStream("");
        String encoding="";
        IOUtils.copy(fileInputStream,writer,encoding);
        writer.toString();

        String string = IOUtils.toString(fileInputStream, encoding);

    }

    //文件读写流 按照字节读取
    //构造方法

    private static void readdemo() {
        File file = new File("E:\\doc\\filedemo.txt");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            int n = 0;
            StringBuffer stringBuffer = new StringBuffer();
            while (n != -1) {
                n = inputStream.read();
                System.out.println(n);
                char by = (char) n;
                System.out.println(by);
                stringBuffer.append(by);
            }
            System.out.println(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在或者文件不可读或者文件是目录");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("读取过程存在异常");
            e.printStackTrace();
        }

    }

}
