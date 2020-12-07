package aaron.ren.pragram.Leetcode.code;

/**
 * 求一个集合的所有子集
 * 两种方式 一种是 递归
 * 2.用二进制位的思想 进行依次遍历即可
 */
public class Subset {

    public static void main(String[] args) {
        char[] a = {'a', 'b', 'c', 'd'};
        subSet(a);
    }

    private static void subSet(char[] a) {
        int n = a.length;
        for (int i = 0; i < (1 << n); i++) {//循环2^n次
            String setStr = Integer.toBinaryString(i);//将int值转换成二进制值的字符串
            int unChoose = n - setStr.length();
            System.out.print("{");
            for (int j = 0; j < setStr.length(); j++) {
                if (setStr.charAt(j) == '1')//1表示被选中，0表示没有被选中
                {
                    System.out.print(a[unChoose + j]);
                }
            }
            System.out.println("}");
        }
    }

}
