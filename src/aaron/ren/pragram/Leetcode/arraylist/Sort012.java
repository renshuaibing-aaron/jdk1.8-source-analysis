package aaron.ren.pragram.Leetcode.arraylist;

import java.util.Arrays;

/**
 * 一个数组 只有0 1 2  对其进行排序 保证时间复杂度是o(n)
 * 设置三个指针 lp0 lp1 lp2
 *
 */
public class Sort012 {

    public static void main(String[] args) {
        int[] arrs={2,0,1,2,0,1,2,0,1,2,0,1};
        //sortarray2(arrs);
        System.out.println(Arrays.toString(arrs));
        System.out.println(Arrays.toString(arrs));
    }


    /*借鉴《算法》中，当数组中存在大量重复元素时，对快排partition方法的改进。
     *即三个指针，把数组划分成三部分，<x、=x、>x*/
    public  static  void sortarray2(int[] nums){
        int a = 0;//指向下一个存放0的位置
        int b = nums.length-1;//从右向左，下一个存放2的位置
        int i = a;
        while(i<=b){
            if(nums[i] == 1) {
                i++;
            } else if(nums[i] == 2) {
                exchange(nums,i,b--);
            } else {
                exchange(nums,i++,a++);
            }
        }
    }
    /*只能遍历一遍，从左向右遍历*/
    public static  void sortarray1(int[] nums){
        int i=-1;
        int j=-1;
        int k=-1;
        for(int p=0;p<nums.length;p++){
            if(nums[p] == 0){
                if(i == -1) {
                    i = 0;//第一个0的位置
                }
                if(j != -1){
                    for(int t=p;t>j;t--) {
                        nums[t] = nums[t-1];
                    }
                    nums[j] = 0;
                    j++;
                    if(k != -1) {
                        k++;
                    }
                }
                else if(k != -1){
                    for(int t=p;t>k;t--) {
                        nums[t] = nums[t-1];
                    }
                    nums[k] = 0;
                    k++;
                }
                else {
                    ;
                }
            }
            else if(nums[p] == 1){
                if(j == -1){
                    if(k != -1) {
                        j = k;
                    } else {
                        j = p;
                    }
                }
                if(k != -1){
                    exchange(nums,p,k);
                    k++;
                }
            }
            else if(nums[p] == 2){
                if(k == -1) {
                    k = p;
                }
            }
            else {
                ;
            }
        }
    }

    public static void exchange(int[] nums, int a, int b){
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

}



































