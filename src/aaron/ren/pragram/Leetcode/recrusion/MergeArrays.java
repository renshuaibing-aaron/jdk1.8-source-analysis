package aaron.ren.pragram.Leetcode.recrusion;

/**
 * https://www.cnblogs.com/lzxin/p/11888176.html
 *�ϲ�N���������飬ÿ������ĳ���ΪM���ϲ�ΪN*M���������顣ʱ�临�Ӷ�Ҫ�����
 */
public class MergeArrays {

    public static void main(String[] args) {
        int[][] nums = new int[][]{{1, 5, 11, 23}, {3, 6, 8, 20}, {2, 4, 7, 15}, {9, 10, 19, 28}};
        for (int num : mergeArrays(nums)) {
            System.out.print(num + " ");
        }
    }


    //��N������ϲ�Ϊһ������
    public static int[] mergeArrays(int[][] nums) {
        return merge(nums);
    }

    //�����ϲ�����
    public static int[] merge(int[][] nums) {
        if (nums.length == 0) {
            return null;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int rows = nums.length / 2;
        //�����ϲ����������
        int[][] result = new int[rows][];
        for (int i = 0; i < nums.length; i += 2) {
            result[i / 2] = mergeTwoArray(nums[i], nums[i + 1]);
        }
        return merge(result);
    }


    //���������������кϲ�����Ϊһ��������
    public static int[] mergeTwoArray(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int[] helper = new int[length1 + length2];
        int p1 = 0, p2 = 0, index = 0;
        while (p1 < length1 && p2 < length2) {
            if (nums1[p1] < nums2[p2]) {
                helper[index++] = nums1[p1++];
            } else {
                helper[index++] = nums2[p2++];
            }
        }
        while (p1 < length1) {
            helper[index++] = nums1[p1++];
        }
        while (p2 < length2) {
            helper[index++] = nums2[p2++];
        }
        return helper;
    }
}