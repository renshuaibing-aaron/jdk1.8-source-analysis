package aaron.ren.pragram.Leetcode.arraylist;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ��һ�������Ŀ��ֵ���������к�ΪĿ��ֵ��������ϣ�������ÿ��ֵ�������ظ�ʹ��
 * ���磺���顾2��3��6��7����Ŀ��ֵ7
 * ������ϣ���7��
 */
public class FindSum2 {

    //��ֵ�����ظ�ʹ��ʱ��ֻ��Ҫÿ�δӸ��ڵ�֮���������
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
            //�˴���i+1��ʼ
            dfs(arr, i + 1, sum - arr[i], ls);
            ls.remove(ls.size() - 1);
        }

    }

}
