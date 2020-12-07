package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 *
 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class LC123 {

    public int maxProfit(int[] prices) {
        int m = prices.length;
        int[][][] dp = new int[m+1][2][3];
        for(int i = m-1; i >= 0; i--) {
            for(int j = 1; j >= 0; j--) {
                for(int k = 2; k >= 0; k--) {
                    if(k == 2 && j == 0) {
                        continue;
                    }
                    if(j > 0) {
                        dp[i][j][k] = Math.max(prices[i] + dp[i+1][0][k],
                                dp[i+1][1][k]);
                    } else {
                        dp[i][j][k] = Math.max(-prices[i] + dp[i+1][1][k+1],
                                dp[i+1][0][k]);
                    }
                }
            }
        }
        return dp[0][0][0];
    }
}

