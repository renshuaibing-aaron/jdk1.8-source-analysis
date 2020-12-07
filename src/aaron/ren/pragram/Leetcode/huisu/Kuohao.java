package aaron.ren.pragram.Leetcode.huisu;

import java.util.ArrayList;
import java.util.List;

public class Kuohao {

    public static void main(String[] args) {
        List<String> strings = generateParenthesis(3);
        for(String str :strings){
            System.out.println(str);
        }
    }

    // ������

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        // ����
        if (n == 0) {
            return res;
        }

        // ִ��������ȱ������������ܵĽ��
        dfs("", n, n, res);
        return res;
    }

    /**
     * @param curStr ��ǰ�ݹ�õ��Ľ��
     * @param left   �����Ż��м�������ʹ��
     * @param right  �����Ż��м�������ʹ��
     * @param res    �����
     */
    private static void dfs(String curStr, int left, int right, List<String> res) {
        // ��Ϊÿһ�γ��ԣ���ʹ���µ��ַ��������������������
        // �ڵݹ���ֹ��ʱ��ֱ�Ӱ�����ӵ���������ɣ�ע���롸���ۡ��� 46 �⡢�� 39 ������
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }

        // ��֦����ͼ�������ſ���ʹ�õĸ����ϸ���������ſ���ʹ�õĸ������ż�֦��ע�����ϸ�ڣ�
        if (left > right) {
            return;
        }

        if (left > 0) {
            dfs(curStr + "(", left - 1, right, res);
        }

        if (right > 0) {
            dfs(curStr + ")", left, right - 1, res);
        }
    }
}