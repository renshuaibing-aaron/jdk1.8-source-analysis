package aaron.ren.jvm;

import java.util.ArrayList;
import java.util.List;

public class HeapOomMock {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<byte[]>();
        int i = 0;
        boolean flag = true;
        while (flag){
            try {
                i++;
                list.add(new byte[1024 * 1024]);//ÿ������һ��1M��С���������
            }catch (Throwable e){
                e.printStackTrace();
                flag = false;
                System.out.println("count="+i);//��¼���еĴ���
            }
        }
    }
}