package aaron.ren.jvm.classloader;

import java.io.FileInputStream;

class HClassLoader extends ClassLoader {

    private String classPath;

    public HClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = loadByte(name);
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }

    }

    /**
     * ��ȡ.class���ֽ���
     *
     * @param name
     * @return
     * @throws Exception
     */
    private byte[] loadByte(String name) throws Exception {
        name = name.replaceAll("\\.", "/");
        FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();

        // �ֽ�������
        data = DESInstance.deCode("1234567890qwertyuiopasdf".getBytes(), data);

        return data;
    }
}