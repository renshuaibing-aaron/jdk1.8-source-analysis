package aaron.ren.pragram.bytedancer.leetcode.arrays;

import java.util.Arrays;

public class Rotate {

    //妈的 这个地方应该用什么思路  利用双指针它不香吗
    public static void main(String[] args) {

        int[] nums={1,2,3,4,5,6,7};
        rotate(nums,3);

        System.out.println(Arrays.toString(nums));
    }
    //输入: [1,2,3,4,5,6,7] 和 k = 3
    //输出: [5,6,7,1,2,3,4]
    // 旋转数组
     //k=1    7 1 2 3 4 5 6
    //k=2     6 7 1 2 3 4 5
    //k=3     5 6 7 1 2 3 4
    //k=4     4 5 6 7 1 2 3
    //k=5     3 4 5 6 7 1 2
    //k=6     2 3 4 5 6 7 1
    //k=7     1 2 3 4 5 6 7
    public static void rotate(int[] nums, int k) {

        int real=k%nums.length;

        //反转数组 怎么使用
        //反转前n-k   反转后k
        //反转整体
        int i=0;
        int j=nums.length-real-1;

        while(i<j){
            int temp =nums[i] ;
            nums[i]=nums[j];
            nums[j]=temp;
            i++;
            j--;

        }
        int l=nums.length-real;
        int m=nums.length-1;
        while(l<m){
            int temp =nums[l] ;
            nums[l]=nums[m];
            nums[m]=temp;
            l++;
            m--;

        }

        int p=0;
        int q=nums.length-1;
        while(p<q){
            int temp =nums[p] ;
            nums[p]=nums[q];
            nums[q]=temp;
            p++;
            q--;

        }


    }

    public void reserve(int[] nums){
        int i=0;
        int j=nums.length-1;
        while(i<j){
            int temp =nums[i] ;
            nums[i]=nums[j];
            nums[j]=temp;
            i++;
            j--;

        }

    }
}
