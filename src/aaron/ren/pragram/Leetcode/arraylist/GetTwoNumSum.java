package aaron.ren.pragram.Leetcode.arraylist;

/**
 * 给你一个按递增排序的整数数组，需要找到数组中的两个整数，他们的和等于给定的目标值，然后返回它们的下标。题目假设给你的数组总是有且只有一个解，而且同一个元素不能使用两次，另外返回的下标要从1开始。
 * 如果找不到我们返回[-1,-1]
 */
public class GetTwoNumSum {
    //Time:O(n),Space:O(1)
    public int[] getTwoNumSumToGivenValue(int[] numbers,int target) {
        int i = 0, j = numbers.length - 1;
        while(i < j) {
            if(numbers[i] + numbers[j] > target) {
                --j;
            } else if(numbers[i] + numbers[j] < target) {
                ++i;
            } else {
                return  new int[]{i + 1, j + 1};
            }
        }
        return new int[] {-1,-1};
    }
}
