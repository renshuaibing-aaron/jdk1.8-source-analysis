package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 * ����һ��δ��������������飬�ҵ���������ĵĵ������С�
 * ����: [1,3,5,4,7]
 * ���: 3
 * ����: ��������������� [1,3,5], ����Ϊ3��
 * ���� [1,3,5,7] Ҳ�������������, �������������ģ���Ϊ5��7��ԭ�����ﱻ4������
 */
public class LeetCode674 {

    public static void main(String[] args) {
        int[] num = {1, 2, 3, 4, 7, 2, 9};
        int lengthOfLCIS = findLengthOfLCIS(num);
        System.out.println(lengthOfLCIS);
    }

    public static int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int max = 0;
        int count = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                count++;
            } else {
                max = Math.max(count, max);
                count = 1;
            }
        }
        max = Math.max(count, max);
        return max;
    }
}
