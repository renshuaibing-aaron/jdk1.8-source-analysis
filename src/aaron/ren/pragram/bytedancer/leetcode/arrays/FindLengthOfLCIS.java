package aaron.ren.pragram.bytedancer.leetcode.arrays;
/*输入: [1,3,5,4,7]
        输出: 3
        解释: 最长连续递增序列是 [1,3,5], 长度为3。
        尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
        示例 2:

        */
public class FindLengthOfLCIS {

    public static void main(String[] args) {
        int [] nums ={1,3,5,4,7};
        System.out.println(findLengthOfLCIS(nums));
    }

    /**
     * 使用滑动窗口
     * @param nums
     * @return
     */
    public int findLengthOfLCISSlidWindows(int[] nums) {
        int ans = 0, anchor = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i > 0 && nums[i-1] >= nums[i]) {
                anchor = i;
            }
            ans = Math.max(ans, i - anchor + 1);
        }
        return ans;
    }

    //最长连续递增序列
    public static  int findLengthOfLCIS(int[] nums) {

        if(nums.length <= 1) {
            return nums.length;
        }
        int count = 1;

        int start = 0,end = 1;

        while(end < nums.length){
            if(nums[end] > nums[end-1]){
                end++;
                continue;
            }
            if(end -start > count) {
                count = end - start;
            }
            start = end++;
        }

        if(end -start > count) {
            count = end - start;  //整个数组都为递增
        }
        return count;

    }

}