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
            if (!file.exists()) {    //���������data.txt�ļ��򴴽�
                file.createNewFile();
                System.out.println("paixu.txt�������");
            }
            FileWriter fw = new FileWriter(file);        //�����ļ�д��
            BufferedWriter bw = new BufferedWriter(fw);

            //����������ݣ�д���ļ�
            Random random = new Random();
            for (int i = 0; i < 100000; i++) {
                int randint = (int) Math.floor((random.nextDouble() * 100000.0));    //����0-10000֮�������
                bw.write(String.valueOf(randint));        //д��һ�������
                bw.newLine();        //�µ�һ��
            }
            bw.close();
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
