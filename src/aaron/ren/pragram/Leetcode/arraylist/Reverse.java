package aaron.ren.pragram.Leetcode.arraylist;

public class Reverse {

    /**
     * rev是循环使用的，每循环一次最后一位就乘以10，并在改数后面拼接上前一位，以此来实现反转。
     * 例如：输入为321,
     * 第一次循环 rev =0, 321的余数pop=1, x除以10后等于32.1，但因为是整型所以自动舍去后面的小数，x=32, rev = 0*10 + 1 = 1
     * 第二次循环 rev =1，x=32的余数pop=2,x除以10后等于3.2，但因为是整型所以自动舍去后面的小数，x=3, rev = 1*10 + 2 = 12
     * 第三次循环 rev =12，x=3的余数pop=3,x除以10后等于0.3，但因为是整型所以自动舍去后面的小数，x=0, rev = 12*10 + 3 = 123
     * @param x
     * @return
     */
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            // 以下等于 x = x/10;
            x /= 10;
            // 大于等于Integer的正整数的最大值返回0
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)){
                return 0;
            }
            // 小于等于Integer的负整数的最大值返回0
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)){
                return 0;
            }

            rev = rev * 10 + pop;
        }
        return rev;

    }

}
