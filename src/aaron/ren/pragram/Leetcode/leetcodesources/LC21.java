package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 * https://www.cnblogs.com/Elaine-DWL/p/11202859.html
 * 给定一个数组，它的第?i 个元素是一支给定股票第 i 天的价格。
 *
 * (3, 5, 7, 3, 8, 1)
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意：你不能在买入股票前卖出股票。
 */
public class LC21 {

    public static void main(String[] args) {
        int[] nums={3, 5, 7, 3, 8, 1};

        System.out.println(maxProfit(nums));
        System.out.println(maxProfit2(nums));
    }

    static   int   maxProfit2(int[] prices) {

        //dp[i]=max{0,prices[i]-currenmin}

        if(prices.length<=1){
            return  0;
        }
        int curmin=prices[0];
        int result=0;
        for(int i=1;i<prices.length;i++){
            result=Math.max(result,prices[i]-curmin);
            curmin=Math.min(curmin,prices[i]);
        }
        return result;
    }
    static   int   maxProfit(int[] prices) {
        // 只允许最多一次交易 求最大收益
        // 记录当前最小  cur_min dp[i]表示在第i天卖出的最大收益
        if(prices.length<=1) {
            return 0;
        }
        int cur_min = prices[0], res = 0;
        for(int i=1; i<prices.length; i++){
            res = Math.max(prices[i]-cur_min, res);// dp[i] = prices[i] - cur_min;
            cur_min = Math.min(cur_min, prices[i]);
        }
        return res;
    }
}



