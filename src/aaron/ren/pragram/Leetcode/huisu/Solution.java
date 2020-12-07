package aaron.ren.pragram.Leetcode.huisu;

import java.util.ArrayList;
import java.util.List;

/**
 * 利用回溯法 求全排列
 */
class Solution {

    public static void main(String[] args) {
     /*   int[] a = {1, 2, 3};
        List<List<Integer>> permute = permute(a);
        for (List<Integer> list : permute) {
            System.out.println(list);
        }
*/
        ArrayList<String> abc = permutation("abc");

        System.out.println(abc);
    }


    public static ArrayList<String> permutation(String str) {
        char[] chars = str.toCharArray();

        ArrayList<String> result= new ArrayList<>();
        dfs2(result,new String(),chars,new boolean[chars.length]);
        return  result;

    }

    public static void dfs2(ArrayList<String> result, String temp, char[] chars, boolean[] used) {
        if (temp.length() == chars.length) {
            //注意不能直接添加temp
            result.add(temp); //以一个集合或数组初始化ArrayList al = new ArrayList(a);//a为集合或数组
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (!used[i]) {//记录某个下标的数是否被使用过
                temp=temp+(chars[i]);
                used[i] = true;
                dfs2(result, temp, chars, used);
                temp= temp.substring(0,temp.length()-1);
                used[i] = false;
            }
        }

    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //回溯算法
        dfs(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }


    //这里是求全排列的方法
    public static void dfs(List<List<Integer>> res, List<Integer> temp, int[] nums, boolean[] used) {
        if (temp.size() == nums.length) {
            //注意不能直接添加temp
            res.add(new ArrayList<>(temp)); //以一个集合或数组初始化ArrayList al = new ArrayList(a);//a为集合或数组
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {//记录某个下标的数是否被使用过
                temp.add(nums[i]);
                used[i] = true;
                dfs(res, temp, nums, used);
                temp.remove(temp.size() - 1); //移除最后一个
                used[i] = false;
            }
        }
    }
}
