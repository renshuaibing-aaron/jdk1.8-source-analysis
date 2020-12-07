package aaron.ren.pragram.Leetcode.leetcodesources;

import java.util.Arrays;

/**
 * �����еĵ� K �����Ԫ��
 * 1.�������� ���� ����
 * 2.���������õݹ�
 * 3.�ѵ�Ӧ��
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

        //  ת��һ�£���  k  ��Ԫ�ص�������  len  -  k
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
     * ��  nums  �����  [left,  right]  ����ִ��  partition  ����������  nums[i]  �����Ժ�Ӧ���ڵ�λ��
     * �ڱ��������б���ѭ��������������
     * 1��(left,  k]  <  nums[left]
     * 2��(k,  i]  >=  nums[left]
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
                //  С��  pivot  ��Ԫ�ض���������ǰ��
                j++;
                swap(nums, j, i);
            }
        }
        //  �����һ����Ҫ������
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
