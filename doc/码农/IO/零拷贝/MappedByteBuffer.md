https://blog.csdn.net/qq_41969879/article/details/81629469
https://www.jianshu.com/p/17e72bb01bf1
FileChannel��
```java
public class MappedByteBufferTest {

    public static void main(String[] args) {
        File file = new File("D://data.txt");
        long len = file.length();
        byte[] ds = new byte[(int) len];

        try {
            MappedByteBuffer mappedByteBuffer = new RandomAccessFile(file, "r")
                    .getChannel()
                    .map(FileChannel.MapMode.READ_ONLY, 0, len);    //�����map������������� ͨ��FileChannel.map���������ļ�ӳ�䵽�����ڴ棬�������߼���ַaddress 
                                                                    //ͨ��newMappedByteBuffer������ʼ��MappedByteBufferʵ��
                    
                    
            for (int offset = 0; offset < len; offset++) {
                byte b = mappedByteBuffer.get();  //MappedByteBuffer��get��������ͨ��DirectByteBuffer.get����ʵ�ֵ�
                //map0()��������һ����ַaddress���������������read��write�������ļ����ж�д��ͨ��address���ܹ������ļ����ײ����unsafe.getByte������ͨ����address + ƫ��������ȡָ���ڴ�����ݡ�
                // ��һ�η���address��ָ����ڴ����򣬵���ȱҳ�жϣ��ж���Ӧ�������ڽ������в������Ӧ��ҳ�棬����Ҳ�����Ҳ���Ǹ��ļ�����û�б������ڴ������������Ӳ���Ͻ��ļ�ָ��ҳ��ȡ�������ڴ��У���jvm���ڴ棩��
                //����ڿ�������ʱ�����������ڴ治���ã����ͨ�������ڴ���ƣ�swap������ʱ���õ�����ҳ�潻����Ӳ�̵������ڴ���
                ds[offset] = b;
            }

            Scanner scan = new Scanner(new ByteArrayInputStream(ds)).useDelimiter(" ");
            while (scan.hasNext()) {
                System.out.print(scan.next() + " ");
            }

        } catch (IOException e) {}
    }

}

```


ʹ��mmap��Ŀ���ǽ��ں��ж��������ĵ�ַ���û��ռ�Ļ���������ӳ�� �Ӷ�ʵ���ں˻�������Ӧ�ó����ڴ�Ĺ��� ʡȥ�˽����ݴ��ں�
���������������û��������Ĺ��� ��������Ҫ��ס �ں˶���������Ȼ��Ҫ������copy��Socket������
