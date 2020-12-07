package aaron.ren.pragram.Leetcode.leetcodesources;

import java.util.List;

/**
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 *   �Զ����µ���С·����Ϊ 11������2 + 3 + 5 + 1 = 11����
 * ]
 */
public class LC120 {
    /**
     * �ⷨ1 ��ά����������
     * @param triangle
     * @return
     */
    public int minimumTotal1(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0){
            return 0;
        }
        // ��1���Բ��ó�ʼ�����һ��
        int[][] dp = new int[triangle.size()+1][triangle.size()+1];

        for (int i = triangle.size()-1; i>=0; i--){
            List<Integer> curTr = triangle.get(i);
            for(int j = 0 ; j< curTr.size(); j++){
                dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) +curTr .get(j);
            }
        }
        return dp[0][0];
    }
    /**
     * �ⷨ2 һά����������
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0){
            return 0;
        }
        // ֻ��Ҫ��¼ÿһ�����Сֵ����
        int[] dp = new int[triangle.size()+1];

        for (int i = triangle.size() - 1; i >= 0; i--) {
            List<Integer> curTr = triangle.get(i);
            for (int j = 0; j < curTr.size(); j++) {
                //�����dp[j] ʹ�õ�ʱ��Ĭ������һ��ģ���ֵ֮���ɵ�ǰ��
                dp[j] = Math.min(dp[j],dp[j+1]) + curTr.get(j);
            }
        }
        return dp[0];
    }
}
