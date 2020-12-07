package aaron.ren.pragram.Leetcode.leetcodesources;

import java.util.Arrays;

/**
 *  下一个排列
 * 下一个最大的排列
 * 主要是算法 算法搞不懂原理
 * https://www.cnblogs.com/misscai/p/9942497.html
 */
public class LC31NextPermutation {

    public static void main(String[] args) {
        int[] nums={3,2,1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static  void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private static  void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private  static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}