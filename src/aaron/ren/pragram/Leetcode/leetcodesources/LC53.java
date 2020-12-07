package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 * ����һ���������� nums���ҵ�һ���������͵����������飨���������ٰ���һ��Ԫ�أ������������͡�
 * ʾ��:
 * ����: [-2,1,-3,4,-1,2,1,-5,4],
 * ���: 6
 * ����: ����������?[4,-1,2,1] �ĺ����Ϊ 6��
 *
 * ���ֽⷨ
 * https://blog.csdn.net/m0_37925202/article/details/80816684
 */
public class LC53 {

    public static void main(String[] args) {
        //DP����
        //dp[i]=max(nums[i],dp[i-1]+nums[i])
        int[] nums={-2,1,-3,4,-1,2,1,-5,4};

        System.out.println(maxSubArray2(nums));
    }

    public static int maxSubArray2(int[] nums) {

        int sum = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(nums[i], nums[i] + sum);
            if (sum > max) {
                max = sum;

            }

        }

        return max;
    }

    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            res = Math.max(res, sum);
        }
        return res;
    }
}
