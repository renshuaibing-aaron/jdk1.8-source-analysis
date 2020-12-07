package aaron.ren.pragram.Leetcode.leetcodesources;

import java.util.*;

/**
 * ����һ�����ظ�Ԫ�ص����� candidates ��һ��Ŀ���� target ���ҳ� candidates �����п���ʹ���ֺ�Ϊ target ����ϡ�
 * candidates �е����ֿ����������ظ���ѡȡ��
 */
public class LC39 {


    public static void main(String[] args) {
        int[] nums={1,2,3,4,5,6};
        System.out.println(combinationSum(nums, 6));
    }

    public static  List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int len = candidates.length;

        // ������Ϊ����ǰ��ֹ����
        Arrays.sort(candidates);

        dfs(candidates, len, target, 0, new ArrayDeque<>(), res);
        return res;
    }

    /**
     * @param candidates ��������
     * @param len        ��������ĳ��ȣ��������
     * @param residue    ʣ����ֵ
     * @param begin      ��������������±�
     * @param path       �Ӹ���㵽�������·��
     * @param res        ���������
     */
    private static void dfs(int[] candidates,
                     int len,
                     int residue,
                     int begin,
                     Deque<Integer> path,
                     List<List<Integer>> res) {
        if (residue == 0) {
            // ���� path ȫ��ֻʹ��һ�ݣ���Ҷ�ӽ���ʱ����Ҫ��һ������
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            // �����������ǰ���£���֦
            if (residue - candidates[i] < 0) {
                break;
            }
            path.addLast(candidates[i]);
            dfs(candidates, len, residue - candidates[i], i, path, res);
            path.removeLast();

        }
    }
}

