package aaron.ren.proxy.dynamic;

import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class JdkProxySourceClass {

    /**
     * 保存 JDK 动态代理生产的类
     * @param filePath 保存路径，默认在项目路径下生成 $Proxy0.class 文件
     */
    public static void saveProxyFile(String... filePath) {
        if (filePath.length == 0) {
            System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        } else {
            FileOutputStream out = null;
            try {
                byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", SayImpl.class.getInterfaces());
                out = new FileOutputStream(filePath[0] + "$Proxy0.class");
                out.write(classFile);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.flush();
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
