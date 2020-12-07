package aaron.ren.pragram.Leetcode.dynamic;

/**
 * 长度为n的方格，刷3种颜色，相邻方格颜色不同，首尾颜色不同，共多少种涂色方式
 * https://blog.csdn.net/universe_ant/article/details/72993809
 */
public class ColorQuestion {
    public long getSolutionNums(int n) {
        long[] nums = new long[51];
        nums[0] = 3;
        nums[1] = 6;//A(3,2)
        nums[2] = 6;//A(3,3)
        for (int i = 3; i < n; i++) {//for n=i, condition on (i-1)th color
            nums[i] = nums[i - 1] + 2 * nums[i - 2];
        }
        return nums[n - 1];
    }
}
