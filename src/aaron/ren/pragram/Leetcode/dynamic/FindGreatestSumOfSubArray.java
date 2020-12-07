package aaron.ren.pragram.Leetcode.dynamic;

/**
 * 利用动态规划的思路求解Java 连续子数组的最大和（超容易理解）
 * dp dp(i)=max(dp(i-1)+array[i],array[i])
 * {6,-3,-2,7,-15,1,2,2},
 *
 */
public class FindGreatestSumOfSubArray {
    public int FindGreatestSumOfSubArray(int[] array) {
        //max就是上面的dp[i]
        int max = array[0];
        //因为这个dp[i]老是变，所以比如你dp[4]是8 dp[5]就变成-7了，所以需要res保存一下
        int res = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max + array[i], array[i]);
            res = Math.max(res, max);
        }
        return res;
    }
}
