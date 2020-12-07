package aaron.ren.pragram.bytedancer.leetcode.backtracking;

import java.util.*;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 */
public class CombinationSum2 {

    // 回溯算法
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        int length = candidates.length;
        List<List<Integer>>  res=new ArrayList<>();

        if(length==0){
            return res;
        }
        //进行先排序
        Arrays.sort(candidates);
        //用来存放临时变量
        Deque<Integer> path = new ArrayDeque<>(length);
        dfs(candidates,0,target,path,res);
        return res;

    }

    // TODO: 2020/8/5    关键信息 采用双端队列
    //                    大小剪枝的原因  DFS的一般写法
    /**
     *
     * @param nums
     * @param begin
     * @param residue
     * @param deque
     * @param res
     */
    private void dfs(int[] nums,int begin,int residue,Deque<Integer> deque,List<List<Integer>>  res) {
   if(residue==0){
       res.add(new ArrayList<Integer>(deque));
   }
   for(int i=begin;i<nums.length;i++){
       if(residue-nums[i]<0){
           break;
       }
       // 小剪枝
       if (i > begin && nums[i] == nums[i - 1]) {
           continue;
       }
       deque.addLast(nums[i]);
       // 因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i
       dfs(nums, i + 1, residue - nums[i], deque, res);

       deque.removeLast();

   }

    }
}
