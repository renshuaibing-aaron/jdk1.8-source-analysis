package aaron.ren.pragram.Leetcode.leetcodesources;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。
 *
 * 输入: nums =
 * [1,3,-1,-3,5,3,6,7] k = 3
 * 输出:
 * [3,3,5,5,6,7]
 */
public class LC239slidewindow {

    public static void main(String[] args) {
        int[] nums={1,3,-1,-3,5,3,6,7};

        System.out.println(Arrays.toString(maxSlidingWindow(nums, 3)));
    }



    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < 0 || k <= 0 || k == 1) {
            return nums;
        }

        //利用最大堆即可
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int[] max = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            //          如果是第K个数之前和第K个数，就说明优先队列没有满，继续添加
            if (i < k - 1) {
                queue.add(nums[i]);
            } else if (i == k - 1) {
                queue.add(nums[i]);
                max[0] = queue.peek();
            } else {
                //              优先队列已满，删除滑动窗口最左边的数[i - k],添加新的数
                queue.remove(nums[i - k]);
                queue.add(nums[i]);
                max[i - k + 1] = queue.peek();
            }
        }
        return max;

    }

    public int[] maxSlidingWindow2(int[] nums, int k) {

        if(!(nums instanceof int[]) || nums == null || nums.length == 0)//判断传进来的是否为int数组，int数组是否为空，int数组是否没有数据
        {
            return new int[0];
        }

        ArrayDeque<Integer> adq = new ArrayDeque<Integer>(k);
        int[] max = new int[nums.length + 1 - k];//获得该nums数组滑动窗口的个数
        for(int i = 0; i < nums.length; i++){
            //每当新数进来，如果发现队列的头部的数的下标是窗口最左边的下标，则移出队列
            if(!adq.isEmpty() && adq.peekFirst() == i - k) {
                adq.removeFirst();
            }
            //把队列尾部的数和新数一一比较，比新数小的都移出队列，直到该队列的末尾数比新数大或者队列为空的时候才停止，保证队列是降序的
            while(!adq.isEmpty() && nums[adq.peekLast()] < nums[i]) {
                adq.removeLast();
            }
            //从尾部加入新的数
            adq.offerLast(i);
            //队列头部就是该窗口最大的数
            if(i >= k -1)//i < k - 1时，滑动窗口才有最大值
            {
                max[i + 1 -k] = nums[adq.peek()];
            }

        }
        return max;



    }


}
