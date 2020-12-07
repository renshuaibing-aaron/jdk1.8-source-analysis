package aaron.ren.pragram.swordtooffer;

/**
 * 约瑟夫环问题 关键是如何找出数学公式
 * https://blog.csdn.net/littlehaes/article/details/94760713
 */
public class LastRemaining {
    public int lastRemaining(int n, int m) {
        if(n==1) {
            return 0;
        }
        return (lastRemaining(n-1, m) + m) % n;
    }
}
