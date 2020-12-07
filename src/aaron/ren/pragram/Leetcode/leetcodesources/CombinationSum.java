package aaron.ren.pragram.Leetcode.arraylist;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * 说明：
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSum {

    public static void main(String[] args) {
        List<List<Integer>> lists = combinationSum3(3, 9);
        System.out.println(lists);
    }
    // k个数 和为n
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (k < 1 || n < 1) {
            return res;
        }
        ArrayList<Integer> list = new ArrayList<>();
        // 回溯递归
        backtrack(k, n, res, list, 1);
        return res;
    }

    private static void backtrack(int k, int target, List<List<Integer>> res, ArrayList<Integer> list, int start) {
        //先判断
        if (target == 0 && k == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (target < 0 || k <= 0)  //后判断 因为都有k=0情况
        {
            return;
        }
        for (int i = start; i <= 9 && target >= i; i++) {
            list.add(i);
            backtrack(k - 1, target - i, res, list, i + 1);
            list.remove(list.size() - 1);
        }

    }
}
