package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 *
 * todo 贪心算法
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 */
public class LC45 {

    // 5 2 3 4 5
    public static void main(String[] args) {
        int[] nums={5,2,3,4,5};

        System.out.println(jump(nums));
    }

    public static  int jump(int[] nums) {
        int length=nums.length;
        int step=0;
        int temp=nums[0];
        int max=0;
        for(int n=0;n<length;n++){//考虑下0的问题
            if (nums.length==1) {
                return 0;
            }
            if(temp>=length-1){
                step=step+1;
                break;
            }
            if (temp>=n){
                if (max<nums[n]+n){
                    max=nums[n]+n;
                }
                if (temp==n){
                    temp=max;
                    step++;
                    max=0;
                }
            }


        }
        return step;
    }
}
