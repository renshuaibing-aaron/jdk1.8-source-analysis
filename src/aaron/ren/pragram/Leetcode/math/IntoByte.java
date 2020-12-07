package aaron.ren.pragram.Leetcode.math;

public class IntoByte {
    static int bytes2int(byte[] b)
    {
        //byte[] b=new byte[]{1,2,3,4};
        int mask=0xff;
        int temp=0;
        int res=0;
        for(int i=0;i<4;i++){
            res<<=8;
            temp=b[i]&mask;
            res|=temp;
        }
        return res;
    }


    static byte[] int2bytes(int num) {
        byte[] b = new byte[4];
        //int mask=0xff;
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (num >>> (24 - i * 8));
        }
        return b;
    }
}
