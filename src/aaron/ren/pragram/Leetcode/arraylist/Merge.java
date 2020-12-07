package aaron.ren.pragram.Leetcode.arraylist;

import java.util.Arrays;

/**
 * 两个有序数组合并
 */
public class Merge {

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 6, 8, 9, 0, 0, 0, 0};
        int[] nums2 = {2, 4, 6, 10};
        merge(nums1, 6, nums2, 4);

        System.out.println(Arrays.toString(nums1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (j >= 0) {
            nums1[k--] = i >= 0 && nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }
    }
}
