1.怎么理解BIO中InputStream/OutputStream 和NIO中的Channel比较
BIO是阻塞的IO 面向流  单向的 操作byte[]  
操作文件常见的使用
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

NIO 是非阻塞的IO 面向缓冲 双向的 操作ByteBuffer
操作文件常见使用
```java
public class NIOTest {

    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);//①
        Path path = Paths.get(System.getProperty("user.dir") + "/assets/file.txt");
        FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ);//②
        int len = fileChannel.read(byteBuffer);//③
        while (len != -1) {
            byteBuffer.flip();//④
            while (byteBuffer.hasRemaining()){
                System.out.print((char) byteBuffer.get());//⑤
            }
            byteBuffer.clear();//⑥
            len = fileChannel.read(byteBuffer);//⑦
        }
    }
}
```

其实查看源码 可以知道这两个读取文件的方式其实在底层是一样的