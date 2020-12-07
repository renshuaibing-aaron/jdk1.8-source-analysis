package aaron.ren.pragram.Leetcode.leetcodesources;

import java.util.ArrayList;
import java.util.List;

/**
 * �ҳ��������֮��Ϊ n �� k ��������ϡ������ֻ������ 1 - 9 ��������������ÿ������в������ظ������֡�
 * ˵����
 * �������ֶ�����������
 * �⼯���ܰ����ظ�����ϡ�
 * ʾ�� 1:
 * ����: k = 3, n = 7
 * ���: [[1,2,4]]
 * ʾ�� 2:
 * ����: k = 3, n = 9
 * ���: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class LeetCode216 {

    public static void main(String[] args) {
        List<List<Integer>> lists = combinationSum3(3, 9);
        System.out.println(lists);
    }
    // k���� ��Ϊn
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (k < 1 || n < 1) {
            return res;
        }
        ArrayList<Integer> list = new ArrayList<>();
        // ���ݵݹ�
        backtrack(k, n, res, list, 1);
        return res;
    }

    private static void backtrack(int k, int target, List<List<Integer>> res, ArrayList<Integer> list, int start) {
        //���ж�
        if (target == 0 && k == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (target < 0 || k <= 0)  //���ж� ��Ϊ����k=0���
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
