package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 *
 ����һ�����飬���ĵ� i ��Ԫ����һ֧�����Ĺ�Ʊ�ڵ� i ��ļ۸�
 ���һ���㷨�����������ܻ�ȡ�������������������� ���� ���ס�
 ע��: �㲻��ͬʱ�����ʽ��ף���������ٴι���ǰ���۵�֮ǰ�Ĺ�Ʊ����
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

