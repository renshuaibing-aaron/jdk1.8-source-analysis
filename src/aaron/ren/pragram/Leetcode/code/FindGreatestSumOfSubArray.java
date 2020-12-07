package aaron.ren.pragram.Leetcode.code;

/**
 * ���ö�̬�滮��˼·���Java ��������������ͣ���������⣩
 * dp dp(i)=max(dp(i-1)+array[i],array[i])
 * {6,-3,-2,7,-15,1,2,2},
 *
 */
public class FindGreatestSumOfSubArray {
    public int FindGreatestSumOfSubArray(int[] array) {
        //max���������dp[i]
        int max = array[0];
        //��Ϊ���dp[i]���Ǳ䣬���Ա�����dp[4]��8 dp[5]�ͱ��-7�ˣ�������Ҫres����һ��
        int res = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max + array[i], array[i]);
            res = Math.max(res, max);
        }
        return res;
    }
}
