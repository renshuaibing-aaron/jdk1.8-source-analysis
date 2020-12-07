package aaron.ren.nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * https://blog.csdn.net/youxijishu/article/details/104815309/
 * Netty�㿽��֮CompositeByteBufʵ���÷�
 * @author renshuaibing
 * @version 1.0
 * @description: TODO
 * @date 2020/11/18 10:34
 */

public class CompositeByteBufTest {

   public  void  byteArray2ByteBuf(){
       String a = "ccc";
       byte[] bytesSrc = a.getBytes(CharsetUtil.UTF_8);
       ByteBuf byteBuf = Unpooled.buffer();
       byteBuf.writeBytes(bytesSrc);
   }

    public  void  byteArray2ByteBuf2(){
        String a = "ccc";
        byte[] bytesSrc = a.getBytes(CharsetUtil.UTF_8);
        ByteBuf byteBuf = Unpooled.wrappedBuffer(bytesSrc);
        byteBuf.writeBytes(bytesSrc);
    }

    public  void  byteArray2ByteBuf3(){
        String a = "ccc";
        String b = "dddd";
        ByteBuf buf1 = Unpooled.wrappedBuffer(a.getBytes(CharsetUtil.UTF_8));
        ByteBuf buf2 = Unpooled.wrappedBuffer(b.getBytes(CharsetUtil.UTF_8));
        ByteBuf compositeByteBuf = Unpooled.wrappedBuffer(buf1,buf2);

        int size = compositeByteBuf.readableBytes();
        byte[] bytes = new byte[size];
        compositeByteBuf.readBytes(bytes);
        String value = new String(bytes,CharsetUtil.UTF_8);
        System.out.println("composite buff result : " + value);
    }

    /**
     * �����ڶ����ݲ�����ʱ��ʵ���ϲ�û�ж�ÿ��ByteBuf�����ݽ��и��ƣ�
     * �����������㷢�����ݵ�ʱ�򣬻�һ���Դ�compositeByteBuf�ж�ȡ���е�����
     */
    public  void  byteArray2ByteBuf4(){
        String a = "ccc";
        String b = "dddd";
        ByteBuf buf1 = Unpooled.wrappedBuffer(a.getBytes(CharsetUtil.UTF_8));
        ByteBuf buf2 = Unpooled.wrappedBuffer(b.getBytes(CharsetUtil.UTF_8));
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();
        //compositeByteBuf.addComponent(buf1);//��Ҫʹ���������������������writeIndex
        compositeByteBuf.addComponent(true,buf1);//һ��Ҫʹ���������
        if (buf2 != null) {
            compositeByteBuf.addComponent(true, buf2);
        }
        int size = compositeByteBuf.readableBytes();
        byte[] bytes = new byte[size];
        compositeByteBuf.readBytes(bytes);

        String value = new String(bytes,CharsetUtil.UTF_8);
        System.out.println("composite buff result : " + value);
    }
}
