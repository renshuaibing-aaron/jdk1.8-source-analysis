package aaron.ren.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Server {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://t7.baidu.com/it/u=3616242789,1098670747&fm=79&app=86&f=JPEG?w=900&h=1350g");
            HttpURLConnection coon = (HttpURLConnection) url.openConnection();

            BufferedInputStream bis = new BufferedInputStream(coon.getInputStream());

            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:\\tmp\\1.bmp"));
            byte bytes[] = new byte[1024];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
            bis.close();
            bos.close();
            System.out.println("œ¬‘ÿÕÍ≥…");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
