package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 *
 * todo ̰���㷨
 * ����һ���Ǹ��������飬�����λ������ĵ�һ��λ�á�
 * �����е�ÿ��Ԫ�ش������ڸ�λ�ÿ�����Ծ����󳤶ȡ�
 * ���Ŀ����ʹ�����ٵ���Ծ����������������һ��λ�á�
 */
public class LC45 {

    // 5 2 3 4 5
    public static void main(String[] args) {
        int[] nums={5,2,3,4,5};

        System.out.println(jump(nums));
    }

    public static  int jump(int[] nums) {
        int length=nums.length;
        int step=0;
        int temp=nums[0];
        int max=0;
        for(int n=0;n<length;n++){//������0������
            if (nums.length==1) {
                return 0;
            }
            if(temp>=length-1){
                step=step+1;
                break;
            }
            if (temp>=n){
                if (max<nums[n]+n){
                    max=nums[n]+n;
                }
                if (temp==n){
                    temp=max;
                    step++;
                    max=0;
                }
            }


        }
        return step;
    }
}
