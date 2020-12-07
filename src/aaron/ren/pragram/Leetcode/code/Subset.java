package aaron.ren.pragram.Leetcode.code;

/**
 * ��һ�����ϵ������Ӽ�
 * ���ַ�ʽ һ���� �ݹ�
 * 2.�ö�����λ��˼�� �������α�������
 */
public class Subset {

    public static void main(String[] args) {
        char[] a = {'a', 'b', 'c', 'd'};
        subSet(a);
    }

    private static void subSet(char[] a) {
        int n = a.length;
        for (int i = 0; i < (1 << n); i++) {//ѭ��2^n��
            String setStr = Integer.toBinaryString(i);//��intֵת���ɶ�����ֵ���ַ���
            int unChoose = n - setStr.length();
            System.out.print("{");
            for (int j = 0; j < setStr.length(); j++) {
                if (setStr.charAt(j) == '1')//1��ʾ��ѡ�У�0��ʾû�б�ѡ��
                {
                    System.out.print(a[unChoose + j]);
                }
            }
            System.out.println("}");
        }
    }

}
