package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 * ����һ���Ǹ��������飬�����λ������ĵ�һ��λ�á�
 * �����е�ÿ��Ԫ�ش������ڸ�λ�ÿ�����Ծ����󳤶ȡ�
 * �ж����Ƿ��ܹ��������һ��λ�á�
 */
public class LC55 {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        // ֱ��Ѱ��
        int i, m;
        for (i = 0; i < nums.length - 1; i++)
        {
            if (nums[i] == 0) // ֻ��0��ʱ�������޷�������Ѱ��
            {
                for (m = 0; m < i; m++)
                {
                    if (nums[m] + m > i) {
                        break;  // ˵���������ƶ�
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