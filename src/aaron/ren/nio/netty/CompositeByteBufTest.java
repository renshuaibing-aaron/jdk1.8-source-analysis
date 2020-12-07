package aaron.ren.nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * https://blog.csdn.net/youxijishu/article/details/104815309/
 * Netty零拷贝之CompositeByteBuf实际用法
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
     * 这样在对数据操作的时候，实际上并没有对每个ByteBuf的数据进行复制，
     * 在最后向网络层发送数据的时候，会一次性从compositeByteBuf中读取所有的数据
     */
    public  void  byteArray2ByteBuf4(){
        String a = "ccc";
        String b = "dddd";
        ByteBuf buf1 = Unpooled.wrappedBuffer(a.getBytes(CharsetUtil.UTF_8));
        ByteBuf buf2 = Unpooled.wrappedBuffer(b.getBytes(CharsetUtil.UTF_8));
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();
        //compositeByteBuf.addComponent(buf1);//不要使用这个方法，它不会增加writeIndex
        compositeByteBuf.addComponent(true,buf1);//一定要使用这个方法
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
