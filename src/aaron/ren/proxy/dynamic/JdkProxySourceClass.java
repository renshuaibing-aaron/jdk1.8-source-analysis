package aaron.ren.proxy.dynamic;

import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class JdkProxySourceClass {

    /**
     * ���� JDK ��̬������������
     * @param filePath ����·����Ĭ������Ŀ·�������� $Proxy0.class �ļ�
     */
    public static void saveProxyFile(String... filePath) {
        if (filePath.length == 0) {
            System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        } else {
            FileOutputStream out = null;
            try {
                byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", SayImpl.class.getInterfaces());
                out = new FileOutputStream(filePath[0]+"/jdkdynamic" + "$Proxy0.class");
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
