package aaron.ren.pragram.Leetcode.arraylist;

/**
 *Ѱ��Top2������������
 * ������˼  ��ʵ���ҳ����е����������� Ȼ�����ǰ����
 * https://blog.csdn.net/qq_23594799/article/details/105028980
 */
public class FindTop2 {
    public static void main(String[] args) {
        helper(new int[]{2, 1, 4, 5, 8, 3, 7, 10, 11,12, 5});
    }

    public static void helper(int[] nums){
        int left=0,right=1;
        int maxLen=0,prevMaxLen=0;
        int maxStart=0,maxEnd=0;
        int secondStart=0,secondEnd=0;
        while (right<nums.length){
            while(right<nums.length&&nums[right-1]<nums[right]){
                right++;
            }
            if(right-left>maxLen){
                //���µڶ���
                prevMaxLen=maxLen;
                secondStart=maxStart;
                secondEnd=maxEnd;
                //���µ�һ��
                maxLen=right-left;
                maxStart=left;
                maxEnd=right-1;
            }else if(right-left>prevMaxLen){
                //���µڶ��󣬶�Ӧ�ŵڶ����������ڵ�һ���������������
                prevMaxLen=right-left;
                secondStart=left;
                secondEnd=right-1;
            }

            left=right;
            right++;
        }
        for (int i=maxStart;i<=maxEnd;i++){
            System.out.print(nums[i]+"\t");
        }
        System.out.println();
        for(int i=secondStart;i<=secondEnd;i++){
            System.out.print(nums[i]+"\t");
        }

    }
}
