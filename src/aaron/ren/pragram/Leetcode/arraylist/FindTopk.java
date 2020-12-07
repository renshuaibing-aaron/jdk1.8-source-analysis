package aaron.ren.pragram.Leetcode.arraylist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *算法-求数组中topK的递增子数组
 */
public class FindTopk {
    public static void main(String[] args) {
        System.out.println(topKArray(new int[]{2, 1, 4, 5, 8, 3, 7, 10, 11,12, 5,6,7,8},2));
    }
    public static List<List<Integer>> topKArray(int nums[], int k){
        if(k==0){
            return new ArrayList<>();
        }
        int dp[]=new int[nums.length];//dp[i]表示到i的最大上升子序列长度
        for(int i=0;i<dp.length;i++){
            dp[i]=1;//为啥是1呢，因为至少自己也是一个子序列
        }
        for (int i=1;i<nums.length;i++){
            if(nums[i]>nums[i-1]){
                dp[i]=dp[i-1]+1;
            }
        }
        List<List<Integer>> res=new LinkedList<>();
        while (k-->0){
            int curMax=dp[0];
            int currMaxPos=0;
            for (int i=0;i<dp.length;i++){
                if(dp[i]>curMax){
                    curMax=dp[i];
                    currMaxPos=i;
                }
            }
            List<Integer> list=new LinkedList<>();
            while (curMax-->0){
                list.add(0,nums[currMaxPos]);
                dp[currMaxPos]=1;//消除这一段
                currMaxPos--;
            }
            res.add(list);
        }
        return res;
    }
}
