package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 */
public class LC55 {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        // 直接寻找
        int i, m;
        for (i = 0; i < nums.length - 1; i++)
        {
            if (nums[i] == 0) // 只有0的时候会出现无法向后继续寻找
            {
                for (m = 0; m < i; m++)
                {
                    if (nums[m] + m > i) {
                        break;  // 说明能往后移动
                    }
                }
                if (m == i) {
                    return false;
                }
            }
        }
        return true;
    }
}