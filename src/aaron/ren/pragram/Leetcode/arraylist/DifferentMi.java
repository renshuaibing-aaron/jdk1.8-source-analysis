package aaron.ren.pragram.Leetcode.arraylist;

/**
 * 统计有序数组里平方和的数目
 */
public class DifferentMi {

    public static int DifferentMi(int nums[]) {
        int cnt = 0;
        int i = 0, j = nums.length - 1;
        while (i < j) {
            while (i < j && nums[i] * nums[i] == nums[j] * nums[j])
                i++;
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                while (i < j && nums[i] * nums[i] == nums[i + 1] * nums[i + 1])
                    i++;
                i++;
            } else {
                while (i < j && nums[j] * nums[j] == nums[j - 1] * nums[j - 1])
                    j--;
                j--;
            }
            cnt++;
        }
        return cnt;
    }
}
