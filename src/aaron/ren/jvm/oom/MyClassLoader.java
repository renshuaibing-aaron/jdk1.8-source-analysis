package aaron.ren.jvm.oom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try{
            String filePath = "/Users/zhanjun/Desktop/" + name.replace('.', File.separatorChar) + ".class";
            //指定读取磁盘上的某个文件夹下的.class文件：
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            //调用defineClass方法，将字节数组转换成Class对象
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
}