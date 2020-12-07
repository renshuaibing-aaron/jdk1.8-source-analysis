package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 * ����һ�����飬���ĵ�?i ��Ԫ����һ֧������Ʊ�� i ��ļ۸�
 * ��������ֻ�������һ�ʽ��ף������������һ֧��Ʊһ�Σ������һ���㷨�����������ܻ�ȡ���������
 * ע�⣺�㲻���������Ʊǰ������Ʊ��
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
