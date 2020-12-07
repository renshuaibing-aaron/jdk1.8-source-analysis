package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 * 给定一个数组，它的第?i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票。
 */
public class LC121 {
    public int maxProfit(int[] prices) {
        if(prices.length <= 1){
            return 0;
        }

        int min = prices[0];
        int opt = 0;

        for(int i = 1; i < prices.length; i++) {
            opt = Math.max(opt, prices[i] - min);

            if(prices[i] < min) {
                min = prices[i];
            }
        }
        return opt;
    }
}
