package aaron.ren.pragram.bytedancer.leetcode.arrays;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 */
public class MaxSubArray {

    public static void main(String[] args) {
        int[] nums={-2,-1};
        System.out.println(maxSubArray(nums));
    }

    //dp[i]=max{dp[i-1]+nums[i],nums[i]}
    //使用动态方程 需要把状态记录下来
    //利用两次的数学的最大值

    public static  int maxSubArray(int[] nums) {

        if(nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        int result=nums[0];
        int[] temp=new int[nums.length];
        temp[0]=nums[0];
        for(int i=1;i<nums.length;i++){
            temp[i]=Math.max(temp[i-1]+nums[i],nums[i]);
            result=Math.max(result,temp[i]);

        }
        return result;
    }
}
