package aaron.ren.pragram.Leetcode.arraylist;

import java.util.HashMap;

/**
 * 一个数组,求两个值为K的组合
 * 在数组中找到两个数，它们之和等于给定的数字
 * 1.暴力解决 拿第一个数和第二个数 第三个数进行比较  时间复杂度O(N^2)
 * 2.依次把数组中的数字放入到Map中(放入到key中)  然后遍历Map 再遍历中 查看Map中是否存在target-key的元素
 *
 */
public class TwoSum {
    public int[] twoSum1(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        throw new IllegalArgumentException("There is not such two integer!");
    }
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            int leave=target-nums[i];
            if(map.containsKey(leave)){
                return new int[] {map.get(leave),i};
            }
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("There is not such two integer!");
    }
}
