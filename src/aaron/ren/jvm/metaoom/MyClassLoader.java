package aaron.ren.jvm.metaoom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try{
            String filePath = "E:\\HR-code\\20190116\\code\\jdk1.8-source-analysis\\src\\aaron\\ren\\jvm\\metaoom\\OOM.class" ;
            //ָ����ȡ�����ϵ�ĳ���ļ����µ�.class�ļ���
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            //����defineClass���������ֽ�����ת����Class����
            Class<?> clazz = this.defineClass(name, bytes, 0, bytes.length);
            fis.close();
            return clazz;
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return super.findClass(name);
    }

    public static void main(String[] args) throws Exception {
        while (true) {
            Class clazz0 = new MyClassLoader().loadClass("aaron.ren.jvm.metaoom.OOM");
        }
    }
}