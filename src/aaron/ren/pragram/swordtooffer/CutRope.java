package aaron.ren.pragram.swordtooffer;

/**
 * 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1，m<=n），每段绳子的长度记为k[1],...,k[m]。请问k[1]x...xk[m]可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 */
public class CutRope {

    public static void main(String[] args) {
        System.out.println(cutRope(5));
    }
    /**
     * 用动态规划实现
     *
     * @param target
     * @return
     */
    public static int cutRope(int target) {
        if (target == 0) {
            return 0;
        }
        if (target == 1) {
            return 0;
        }
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        int[] result = new int[target+1]; //注意这个是长度需要加1
        result[0] = 1;
        result[1] = 1;
        result[2] = 2;
        result[3] = 3;

        int max = 0;
        //f(n)=max{f(i)f(n-i)}
        //f(4)=max{f(1)f(3),f(2)f(2)}
        //f(5)=max{f(1)f(4),f(2)f(3)}
        //f(6)=max{f(1)f(5),f(2)f(4),f(3)f(3)}
        //f(7)=max{f(1)f(6),f(2)f(5),f(3)f(4)}

        for (int i = 4; i <= target; i++) {
            max = 0;  //这个地方必须重新初始化
            for (int j = 1; j < i; j++) {  //这个地方可以优化
                int product = result[j] * result[i-j];
                if (max < product) {
                    max = product;
                }
                result[i] = max;
            }

        }
        max = result[target];
        return max;

    }
}
