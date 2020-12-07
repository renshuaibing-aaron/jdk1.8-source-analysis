package aaron.ren.pragram.bytedancer.leetcode.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class Permute {
    public static void main(String[] args) {
        int[] nums={1,2,3};
        System.out.println(permute(nums));
    }

    public static  List<List<Integer>> permute(int[] nums) {
        int length = nums.length;
        List<List<Integer>> res=new ArrayList<>();

        if(length==0){
            return res;
        }
        boolean[] isflag=new boolean[nums.length];

        //利用双端队列实现牛逼
        Deque queue=new ArrayDeque(nums.length);
        dfs(res,nums,isflag,queue);
        return res;

    }

    private static void dfs(List<List<Integer>> res, int[] nums, boolean[] isflag, Deque queue) {
        if(queue.size()==nums.length){
           // res.add(tmp);  这里不行
            res.add(new ArrayList<Integer>(queue));  //
        }
        for(int i=0;i<nums.length;i++){

            if(isflag[i]==false){
                isflag[i]=true;
              queue.addLast(nums[i]);
                dfs(res,nums,isflag,queue);
                //怎么回退
                queue.removeLast();
                isflag[i]=false;
            }

        }
    }
}
