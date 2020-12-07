package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 * 给定一个整数数组 nums，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组?[4,-1,2,1] 的和最大，为 6。
 *
 * 三种解法
 * https://blog.csdn.net/m0_37925202/article/details/80816684
 */
public class LC53 {

    public static void main(String[] args) {
        //DP问题
        //dp[i]=max(nums[i],dp[i-1]+nums[i])
        int[] nums={-2,1,-3,4,-1,2,1,-5,4};

        System.out.println(maxSubArray2(nums));
    }

    public static int maxSubArray2(int[] nums) {

        int sum = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(nums[i], nums[i] + sum);
            if (sum > max) {
                max = sum;

            }

        }

        return max;
    }

    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            res = Math.max(res, sum);
        }
        return res;
    }
}
