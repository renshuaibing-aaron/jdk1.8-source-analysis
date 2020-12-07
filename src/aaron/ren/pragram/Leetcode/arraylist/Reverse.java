package aaron.ren.pragram.Leetcode.arraylist;

public class Reverse {

    /**
     * rev��ѭ��ʹ�õģ�ÿѭ��һ�����һλ�ͳ���10�����ڸ�������ƴ����ǰһλ���Դ���ʵ�ַ�ת��
     * ���磺����Ϊ321,
     * ��һ��ѭ�� rev =0, 321������pop=1, x����10�����32.1������Ϊ�����������Զ���ȥ�����С����x=32, rev = 0*10 + 1 = 1
     * �ڶ���ѭ�� rev =1��x=32������pop=2,x����10�����3.2������Ϊ�����������Զ���ȥ�����С����x=3, rev = 1*10 + 2 = 12
     * ������ѭ�� rev =12��x=3������pop=3,x����10�����0.3������Ϊ�����������Զ���ȥ�����С����x=0, rev = 12*10 + 3 = 123
     * @param x
     * @return
     */
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            // ���µ��� x = x/10;
            x /= 10;
            // ���ڵ���Integer�������������ֵ����0
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)){
                return 0;
            }
            // С�ڵ���Integer�ĸ����������ֵ����0
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)){
                return 0;
            }

            rev = rev * 10 + pop;
        }
        return rev;

    }

}
