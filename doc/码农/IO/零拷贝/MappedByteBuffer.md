https://blog.csdn.net/qq_41969879/article/details/81629469
https://www.jianshu.com/p/17e72bb01bf1
FileChannel的
```java
public class MappedByteBufferTest {

    public static void main(String[] args) {
        File file = new File("D://data.txt");
        long len = file.length();
        byte[] ds = new byte[(int) len];

        try {
            MappedByteBuffer mappedByteBuffer = new RandomAccessFile(file, "r")
                    .getChannel()
                    .map(FileChannel.MapMode.READ_ONLY, 0, len);    //这里的map方法大概有两步 通过FileChannel.map方法，把文件映射到虚拟内存，并返回逻辑地址address 
                                                                    //通过newMappedByteBuffer方法初始化MappedByteBuffer实例
                    
                    
            for (int offset = 0; offset < len; offset++) {
                byte b = mappedByteBuffer.get();  //MappedByteBuffer的get方法最终通过DirectByteBuffer.get方法实现的
                //map0()函数返回一个地址address，这样就无需调用read或write方法对文件进行读写，通过address就能够操作文件。底层采用unsafe.getByte方法，通过（address + 偏移量）获取指定内存的数据。
                // 第一次访问address所指向的内存区域，导致缺页中断，中断响应函数会在交换区中查找相对应的页面，如果找不到（也就是该文件从来没有被读入内存的情况），则从硬盘上将文件指定页读取到物理内存中（非jvm堆内存）。
                //如果在拷贝数据时，发现物理内存不够用，则会通过虚拟内存机制（swap）将暂时不用的物理页面交换到硬盘的虚拟内存中
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


使用mmap的目的是将内核中读缓冲区的地址和用户空间的缓冲区进行映射 从而实现内核缓冲区与应用程序内存的共享 省去了将数据从内核
读缓冲区拷贝到用户缓冲区的过程 ，但是需要记住 内核读缓冲区仍然需要将数据copy到Socket缓冲区
