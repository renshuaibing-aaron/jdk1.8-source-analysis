package aaron.ren.pragram.Leetcode.huisu;

import java.util.ArrayList;
import java.util.List;

/**
 * ���û��ݷ� ��ȫ����
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
            //ע�ⲻ��ֱ�����temp
            result.add(temp); //��һ�����ϻ������ʼ��ArrayList al = new ArrayList(a);//aΪ���ϻ�����
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (!used[i]) {//��¼ĳ���±�����Ƿ�ʹ�ù�
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
        //�����㷨
        dfs(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }


    //��������ȫ���еķ���
    public static void dfs(List<List<Integer>> res, List<Integer> temp, int[] nums, boolean[] used) {
        if (temp.size() == nums.length) {
            //ע�ⲻ��ֱ�����temp
            res.add(new ArrayList<>(temp)); //��һ�����ϻ������ʼ��ArrayList al = new ArrayList(a);//aΪ���ϻ�����
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {//��¼ĳ���±�����Ƿ�ʹ�ù�
                temp.add(nums[i]);
                used[i] = true;
                dfs(res, temp, nums, used);
                temp.remove(temp.size() - 1); //�Ƴ����һ��
                used[i] = false;
            }
        }
    }
}
