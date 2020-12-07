package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 * ����һ���������� nums ��һ��Ŀ��ֵ target�������ڸ��������ҳ���ΪĿ��ֵ���� ���� ���������������ǵ������±ꡣ
 *
 * ����Լ���ÿ������ֻ���Ӧһ���𰸡����ǣ��㲻���ظ��������������ͬ����Ԫ�ء�
 */
public class LC1 {


    public static void main(String[] args) {
        int[] nums={1,2,3,4,5,6,7};
        int[] ints = twoSum(nums, 8);
        System.out.println(ints[0]+"----"+ints[1]);
    }

    public static int[] twoSum(int[] nums, int target)
    {
        for(int i=0; i<nums.length; i++)
        {
            for(int j=i+1; j<nums.length; j++)
            {
                if(target - nums[i] == nums[j]) {
                    return new int[]{i,j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

}
