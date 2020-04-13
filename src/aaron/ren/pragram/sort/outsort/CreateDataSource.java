package aaron.ren.pragram.sort.outsort;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class CreateDataSource {

    static {
        createSources();
    }

    public static void main(String[] args) {


    }


    private static void createSources() {

        String filepath = System.getProperty("user.dir");
        filepath += "\\paixu.txt";
        System.out.println(filepath);

        try {
            File file = new File(filepath);
            if (!file.exists()) {    //如果不存在data.txt文件则创建
                file.createNewFile();
                System.out.println("paixu.txt创建完成");
            }
            FileWriter fw = new FileWriter(file);        //创建文件写入
            BufferedWriter bw = new BufferedWriter(fw);

            //产生随机数据，写入文件
            Random random = new Random();
            for (int i = 0; i < 100000; i++) {
                int randint = (int) Math.floor((random.nextDouble() * 100000.0));    //产生0-10000之间随机数
                bw.write(String.valueOf(randint));        //写入一个随机数
                bw.newLine();        //新的一行
            }
            bw.close();
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
