package aaron.ren.jvm.cassloder2;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader {
    private String mLibPath;

    public MyClassLoader(String path) {
        mLibPath = path;
    }

    public static void main(String[] args) throws Exception {
        MyClassLoader diskLoader = new MyClassLoader("D:\\tmp\\");
        //˫��ί��
        Class<?> c = diskLoader.loadClass("com.xinxin.classloader.Student");

        //�ƹ�˫��ί��
        Class<?> c2 = diskLoader.createClass("com.xinxin.classloader.Student");
        if(c != null){
            try {
                Object obj = c.newInstance();
                Method method = c.getDeclaredMethod("hello",null);
                method.invoke(obj, null);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * ˫��ί���߼�����������������Class�Ż���ô˷���
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        byte[] data;
        try {
            data = readClassFile( name);
            return defineClass(name,data,0,data.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    /**
     * �ƹ�˫��ί���߼���ֱ�ӻ�ȡClass
     */
    public Class<?> createClass(String name) throws Exception{
        byte[] data;
        data = readClassFile(name);
        return defineClass(name,data,0,data.length);
    }

    /**
     * ��ȡClass�ļ�
     */
    private byte[] readClassFile(String name) throws Exception{
        String fileName = getFileName(name);
        File file = new File(mLibPath,fileName);
        FileInputStream is = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len = 0;
        while ((len = is.read()) != -1) {
            bos.write(len);
        }
        byte[] data = bos.toByteArray();
        is.close();
        bos.close();
        return data;
    }

    //��ȡҪ���� ��class�ļ���
    private String getFileName(String name) {
        int index = name.lastIndexOf('.');
        if(index == -1){
            return name+".class";
        }else{
            return name.substring(index+1)+".class";
        }
    }
}
