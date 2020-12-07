package aaron.ren.pragram.Leetcode.leetcodesources;

import java.util.Arrays;

/**
 * 数组中的第 K 个最大元素
 * 1.暴力方法 排序 数组
 * 2.快速排序用递归
 * 3.堆的应用
 * https://blog.csdn.net/kexuanxiu1163/article/details/102830049
 */
public class LeetCode215 {
    public int findKthLargest1(int[] nums, int k) {
        int len = nums.length;
        Arrays.sort(nums);
        return nums[len - k];
    }

    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;

        //  转换一下，第  k  大元素的索引是  len  -  k
        int target = len - k;

        while (true) {
            int index = partition(nums, left, right);
            if (index == target) {
                return nums[index];
            } else if (index < target) {
                left = index + 1;
            } else {
                assert index > target;
                right = index - 1;
            }
        }
    }

    /**
     * 在  nums  数组的  [left,  right]  部分执行  partition  操作，返回  nums[i]  排序以后应该在的位置
     * 在遍历过程中保持循环不变量的语义
     * 1、(left,  k]  <  nums[left]
     * 2、(k,  i]  >=  nums[left]
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int j = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                //  小于  pivot  的元素都被交换到前面
                j++;
                swap(nums, j, i);
            }
        }
        //  最后这一步不要忘记了
        swap(nums, j, left);
        return j;
    }

    private void swap(int[] nums, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
