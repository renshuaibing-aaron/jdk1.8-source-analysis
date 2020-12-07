package aaron.ren.pragram.Leetcode.dynamic;

/**
 * 假设有几种硬币，并且数量无限。请找出能够组成某个数目的找零所使用最少的硬币数。例如几种硬币为[1, 3, 5], 面值2的最少硬币数为2(1, 1),
 * f(0)=0
 * f(1)=1=
 * f(2)=2
 * f(3)=1
 * f(4)=2
 */
public class DynamicCoin {

    public static void main(String[] args) {

        int[] coins = {1, 3, 5};
        System.out.println(coinDynamic(coins, 0));
        System.out.println(coinDynamic(coins, 1));
        System.out.println(coinDynamic(coins, 2));
        System.out.println(coinDynamic(coins, 3));
        System.out.println(coinDynamic(coins, 4));
        System.out.println(coinDynamic(coins, 5));
        System.out.println(coinDynamic(coins, 6));
    }

    /**
     * 利用动态规划
     * @param coins
     * @param k
     * @return
     */
    public static int coinDynamic(int[] coins, int k) {


        int length = coins.length;
        int[] result = new int[k + 1];

        //赋值
        result[0] = 0;
        int i, j;
        //由小到大进行
        for (i = 1; i <= k; i++) {
            //赋值
            result[i] = Integer.MAX_VALUE;
            for (j = 0; j < length; j++) {
                //边界 什么  这个边界 可以根据下面的动态方程获取
                if (i >= coins[j] && result[i - coins[j]] != Integer.MAX_VALUE) {
                    //这个地方很巧妙啊
                    //f(x)=min{f(x-1)+1,f(x-3)+1,f(x-5)+1}
                    result[i] = Math.min(result[i - coins[j]] + 1, result[i]);
                }
            }


        }
        if (result[k] == Integer.MAX_VALUE) {
            result[k] = -1;
        }
        return result[k];
    }

    public static int backTrackingCoin(int[] coins, int k) {//回溯法+动态规划
        if (coins == null || coins.length == 0 || k < 1) {
            return 0;
        }
        int[][] matrix = new int[k + 1][coins.length + 1];
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < coins.length; j++) {
                int preK = i - coins[j];
                if (preK > -1) {//只有在不小于0时, preK才能存在于数组matrix中, 才能够进行回溯.
                    matrix[i][j] = matrix[preK][coins.length] + 1;//面值i在进行回溯
                    if (matrix[i][coins.length] == 0 || matrix[i][j] < matrix[i][coins.length]) {//如果当前的硬币数目是最少的, 更新min列的最少硬币数目
                        matrix[i][coins.length] = matrix[i][j];
                    }
                }
            }
        }
        return matrix[k][coins.length];
    }
}
