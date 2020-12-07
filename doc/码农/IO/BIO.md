1.��ô���BIO��InputStream/OutputStream ��NIO�е�Channel�Ƚ�
BIO��������IO ������  ����� ����byte[]  
�����ļ�������ʹ��
```java
public class BIOTest {
public static void main(String[] args) {
        File file = new File(System.getProperty("user.dir") + "/src/oio/file.txt");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            int len = inputStream.read(bytes);
            System.out.println("bytes len :" + len + " detail: " + new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
```

NIO �Ƿ�������IO ���򻺳� ˫��� ����ByteBuffer
�����ļ�����ʹ��
```java
public class NIOTest {

    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);//��
        Path path = Paths.get(System.getProperty("user.dir") + "/assets/file.txt");
        FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ);//��
        int len = fileChannel.read(byteBuffer);//��
        while (len != -1) {
            byteBuffer.flip();//��
            while (byteBuffer.hasRemaining()){
                System.out.print((char) byteBuffer.get());//��
            }
            byteBuffer.clear();//��
            len = fileChannel.read(byteBuffer);//��
        }
    }
}
```

��ʵ�鿴Դ�� ����֪����������ȡ�ļ��ķ�ʽ��ʵ�ڵײ���һ����