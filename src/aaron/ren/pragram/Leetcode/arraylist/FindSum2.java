package aaron.ren.pragram.Leetcode.arraylist;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 给一个数组和目标值，求数组中和为目标值的所有组合，数组中每个值不可以重复使用
 * 比如：数组【2，3，6，7】，目标值7
 * 所有组合：【7】
 */
public class FindSum2 {

    //当值不能重复使用时，只需要每次从根节点之后遍历即可
    ArrayList<ArrayList<Integer>> list = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        ArrayList<Integer> ls = new ArrayList<>();
        if (num == null || num.length == 0) {
            return list;
        }
        Arrays.sort(num);
        dfs(num, 0, target, ls);
        return list;

    }

    private void dfs(int[] arr, int index, int sum, ArrayList<Integer> ls) {
        if (sum < 0) {
            return;
        }
        if (sum == 0) {
            if (list.contains(ls)) {
                return;
            }
            list.add(new ArrayList<>(ls));
            return;
        }

        for (int i = index; i < arr.length; i++) {
            ls.add(arr[i]);
            //此处从i+1开始
            dfs(arr, i + 1, sum - arr[i], ls);
            ls.remove(ls.size() - 1);
        }

    }

}
