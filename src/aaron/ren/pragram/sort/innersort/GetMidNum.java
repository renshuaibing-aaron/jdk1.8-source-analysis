package aaron.ren.pragram.sort.innersort;

import java.util.Arrays;

public class GetMidNum {

    public static void main(String[] args) {
        int[] nums={1,2,3,4,5};

        System.out.println(getMidNumByQuickSort(nums, 0, 4));
        int[] nums2={1,2,3,4};

        System.out.println(getMidNumByQuickSort(nums2, 0, 3));

    }

    public static  int  getMidNumByQuickSort(int[] nums,int start,int end){
        int left=start;
        int right=end;
        int tmp=nums[end];

        while(left<right){
            while(left<right&&nums[left]<=tmp){
                left++;
            }
            while(left<right&&nums[right]>=tmp){
                right--;
            }
            if(left<right){
                swap(nums,left,right);
            }

        }

        swap(nums,right,end);
        return left;

    }

    private static void swap(int[] nums, int left, int right) {
     int tmp =nums[left];
     nums[left]=nums[right];
     nums[right]=tmp;
    }
}
