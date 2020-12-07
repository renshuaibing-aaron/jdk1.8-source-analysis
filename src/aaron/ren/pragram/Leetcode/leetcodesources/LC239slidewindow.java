package aaron.ren.pragram.Leetcode.leetcodesources;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * ����һ������ nums����һ����СΪ k �Ļ������ڴ������������ƶ�����������Ҳࡣ
 * ��ֻ���Կ����ڻ������� k �ڵ����֡���������ÿ��ֻ�����ƶ�һλ��
 *
 * ����: nums =
 * [1,3,-1,-3,5,3,6,7] k = 3
 * ���:
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

        //�������Ѽ���
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int[] max = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            //          ����ǵ�K����֮ǰ�͵�K��������˵�����ȶ���û�������������
            if (i < k - 1) {
                queue.add(nums[i]);
            } else if (i == k - 1) {
                queue.add(nums[i]);
                max[0] = queue.peek();
            } else {
                //              ���ȶ���������ɾ��������������ߵ���[i - k],����µ���
                queue.remove(nums[i - k]);
                queue.add(nums[i]);
                max[i - k + 1] = queue.peek();
            }
        }
        return max;

    }

    public int[] maxSlidingWindow2(int[] nums, int k) {

        if(!(nums instanceof int[]) || nums == null || nums.length == 0)//�жϴ��������Ƿ�Ϊint���飬int�����Ƿ�Ϊ�գ�int�����Ƿ�û������
        {
            return new int[0];
        }

        ArrayDeque<Integer> adq = new ArrayDeque<Integer>(k);
        int[] max = new int[nums.length + 1 - k];//��ø�nums���黬�����ڵĸ���
        for(int i = 0; i < nums.length; i++){
            //ÿ������������������ֶ��е�ͷ���������±��Ǵ�������ߵ��±꣬���Ƴ�����
            if(!adq.isEmpty() && adq.peekFirst() == i - k) {
                adq.removeFirst();
            }
            //�Ѷ���β������������һһ�Ƚϣ�������С�Ķ��Ƴ����У�ֱ���ö��е�ĩβ������������߶���Ϊ�յ�ʱ���ֹͣ����֤�����ǽ����
            while(!adq.isEmpty() && nums[adq.peekLast()] < nums[i]) {
                adq.removeLast();
            }
            //��β�������µ���
            adq.offerLast(i);
            //����ͷ�����Ǹô���������
            if(i >= k -1)//i < k - 1ʱ���������ڲ������ֵ
            {
                max[i + 1 -k] = nums[adq.peek()];
            }

        }
        return max;



    }


}
