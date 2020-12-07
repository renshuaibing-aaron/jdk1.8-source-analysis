package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 * https://www.cnblogs.com/Elaine-DWL/p/11202859.html
 * ����һ�����飬���ĵ�?i ��Ԫ����һ֧������Ʊ�� i ��ļ۸�
 *
 * (3, 5, 7, 3, 8, 1)
 * ��������ֻ�������һ�ʽ��ף������������һ֧��Ʊһ�Σ������һ���㷨�����������ܻ�ȡ���������
 *
 * ע�⣺�㲻���������Ʊǰ������Ʊ��
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
        // ֻ�������һ�ν��� ���������
        // ��¼��ǰ��С  cur_min dp[i]��ʾ�ڵ�i���������������
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



