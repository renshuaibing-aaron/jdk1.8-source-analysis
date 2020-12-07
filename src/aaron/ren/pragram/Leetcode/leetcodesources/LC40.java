package aaron.ren.pragram.Leetcode.leetcodesources;

import java.util.*;

/**
 * todo
 *    ���Ҫ��39����бȽ� ���û��ݷ�+�ݹ����ʵ��
 *����һ������?candidates?��һ��Ŀ����?target?���ҳ�?candidates?�����п���ʹ���ֺ�Ϊ?target?����ϡ�
 * candidates?�е�ÿ��������ÿ�������ֻ��ʹ��һ�Ρ�
 */
public class LC40 {

    public static void main(String[] args) {
        int[]nums={1,2 ,3,4};
        System.out.println(combinationSum2(nums, 5));
    }

    public static  List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        // �Ƚ�����������һ���ܹؼ�
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(candidates, len, 0, target, path, res);
        return res;
    }

    /**
     * @param candidates ��ѡ����
     * @param len
     * @param begin      �Ӻ�ѡ����� begin λ�ÿ�ʼ����
     * @param residue    ��ʾʣ�࣬���ֵһ��ʼ���� target��������Ŀ��˵����"�������֣�����Ŀ����������������"�������
     * @param path       �Ӹ���㵽Ҷ�ӽ���·��
     * @param res
     */
    private static void dfs(int[] candidates, int len, int begin, int residue, Deque<Integer> path, List<List<Integer>> res) {
        if (residue == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < len; i++) {
            // ���֦
            if (residue - candidates[i] < 0) {
                break;
            }
            // С��֦
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.addLast(candidates[i]);
            // ��ΪԪ�ز������ظ�ʹ�ã�����ݹ鴫����ȥ���� i + 1 ������ i
            dfs(candidates, len, i + 1, residue - candidates[i], path, res);

            path.removeLast();
        }
    }


}

