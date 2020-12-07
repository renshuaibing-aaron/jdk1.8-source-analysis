package aaron.ren.pragram.Leetcode.math;


/**
 * 题目描述：给出一个区间[a, b]，计算区间内“神奇数”的个数。
 * 神奇数的定义：存在不同位置的两个数位，组成一个两位数（且不含前导0），且这个两位数为质数。
 * 比如：153，可以使用数字3和数字1组成13，13是质数，满足神奇数。同样153可以找到31和53也为质数，只要找到一个质数即满足神奇数。
 * 输入描述:输入为两个整数a和b，代表[a, b]区间 (1 ≤ a ≤ b ≤ 10000)。//注意次数要包含a,和b。
 * 输出描述: 输出为一个整数，表示区间内满足条件的整数个数
 *
 * 输入例子: 11 20
 * 输出例子: 6
 *
 * @author 崔洪振367
 * @version 创建时间：2017年5月22日 下午10:29:55
 * 解题思路：从a到b逐个判断，是不是“神奇数”。该题目给出的解决方法中：是通过两层for循环来判断任意组合的两位数中是否包含质数。
 */
public class AmazingNumber  {

    public void amazingNumber1() {
        int tmp=0;
        for(int i=1;i<=9;i++) {
            tmp=tmp*10+i;
            System.out.println(tmp+"*8+"+i+"="+(tmp*8+i));
        }
    }

    public void amazingNumber2() {
        int tmp=0;
        for(int i=1;i<=9;i++) {
            tmp=tmp*10+i;
            int j = i+1;
            System.out.println(tmp+"*9+"+j+"="+(tmp*9+j));
        }
    }

    public void amazingNumber3() {
        int tmp=0;
        for(int i=9;i>=2;i--) {
            tmp=tmp*10+i;
            int j = i-2;
            System.out.println(tmp+"*9+"+j+"="+(tmp*9+j));
        }

    }
}

