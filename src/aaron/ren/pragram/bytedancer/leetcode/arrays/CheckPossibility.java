package aaron.ren.pragram.bytedancer.leetcode.arrays;

public class CheckPossibility {

    /**
     * 1 2 3 4 9 6 7 8   9<6    把9变成4
     * 1 2 3 4 1 6 7 8   4<1    把1
     *
     *
     * 输入: nums = [4,2,1]
     * 输出: false
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {

        //这个题的关键是 变成非递减 怎么处理 关键是把这个异常值 变成前面的还是后面的

        int len = nums.length;
        int count=0;
        // 数组个数小于2个 一定可以
        if(len<3) {
            return true;
        }
        // 思路如下：
        // 如果出现 a[i] > a[i+1]   改变一个数 就面临两种选择
        // 1. 把a[i]变大
        // 2. 把a[i+1] 变小
        // 这两种选择其实是有依据的 需要比较a[i-1] 与 a[i+1]的值
        // eg.  ... 1 4 3 ...   只能选择把4变小   ... 3 4 1 ... 只能选择把1变大
        // 改变完之后，记录改变次数，再检测是否升序
        // 如果次数大于1，至少改了两次 返回false

        // 先让前两个有序
        // 因为没有左边没有数 所以对于前两个数来说，最佳选择就是吧 a[0] 变小
        if(nums[0]>nums[1]){
            nums[0] = nums[1];
            count+=1;
        }
        for(int i=1;i<len-1;i++){
            if(nums[i] > nums[i+1]){
                count +=1;
                if(count > 1) {
                    return false;
                }
                if(nums[i-1] < nums[i+1]) {
                    nums[i]=nums[i-1];
                } else {
                    nums[i+1]=nums[i];
                }
            }
        }
        return true;

    }
}
