package aaron.ren.pragram.bytedancer.leetcode.binarysearch;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 * 示例 1：
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 * 输入：[2,2,2,0,1]
 * 输出：0
 */
public class MinArray {

    public static void main(String[] args) {
        int[] nums={7,8,9,1,2,3,4,5,6};
        int[] nums2={4,5,6,7,8,9,1,2,3};
        System.out.println(minArray(nums));
        System.out.println(minArray(nums2));
    }

    public static  int minArray(int[] numbers) {

        if(numbers.length==1){
           return numbers[0];
        }

        int left = 0;
        int right = numbers.length - 1;
        while(left <= right)
        {
            int mid = (left + right) / 2;
            //int mid = (left + right) >> 1;
             if(numbers[mid] < numbers[right])  //右半部分是递增的
            {
              return Math.min(numbers[left],numbers[mid]);
            }
            else     //左半部分是递增的
            {
             return  Math.min(numbers[left],numbers[mid+1]);
            }
        }
        return -1;
    }
}
