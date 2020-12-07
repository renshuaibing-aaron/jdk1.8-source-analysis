package aaron.ren.pragram.Leetcode.arraylist;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 给一个数组和目标值，求数组中和为目标值的所有组合，数组中每个值可以重复使用
 * 比如：数组【2，3，6，7】，目标值7
 * 所有组合：【2，2，3】，【7】
 */
public class FindSum {

    public static void main(String[] args) {
        int[] nums = {2, 3, 6, 7};
        ArrayList<ArrayList<Integer>> arrayLists = combinationSum(nums, 7);
        System.out.println(arrayLists);
    }

    public static ArrayList<ArrayList<Integer>> combinationSum(int[] num, int target) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Integer> ls = new ArrayList<>();
        if (num == null || num.length == 0) {
            return list;
        }
        Arrays.sort(num);
        dfs(num, 0, target, ls, list);
        return list;
    }

    /**
     *
     * @param arr
     * @param index
     * @param sum
     * @param ls  临时变量
     * @param list 最终变量
     */
    private static void dfs(int[] arr, int index, int sum, ArrayList<Integer> ls, ArrayList<ArrayList<Integer>> list) {
        if (sum < 0) {
            return;
        }
        if (sum == 0) {
            if (!list.contains(ls)) {
                list.add(new ArrayList<>(ls));
            }
            return;
        }
        for (int i = index; i < arr.length; i++) {
            ls.add(arr[i]);
            dfs(arr, i, sum - arr[i], ls, list);
            //回溯到上一层
            ls.remove(ls.size() - 1);
        }

    }

}
