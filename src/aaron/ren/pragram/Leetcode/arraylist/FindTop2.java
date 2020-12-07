package aaron.ren.pragram.Leetcode.arraylist;

/**
 *寻找Top2的升序子数组
 * 明白意思  其实是找出所有的升序子数组 然后输出前两个
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
                //更新第二大
                prevMaxLen=maxLen;
                secondStart=maxStart;
                secondEnd=maxEnd;
                //更新第一大
                maxLen=right-left;
                maxStart=left;
                maxEnd=right-1;
            }else if(right-left>prevMaxLen){
                //更新第二大，对应着第二长度数组在第一长度数组后面的情况
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
