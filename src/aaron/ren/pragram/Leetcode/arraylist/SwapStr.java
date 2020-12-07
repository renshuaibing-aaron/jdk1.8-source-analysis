package aaron.ren.pragram.Leetcode.arraylist;

/**
 * 输入字符串，’010101100' -> '000001111’，一次只能交换两个字符，求最少需要多少次交换
 */
public class SwapStr {

    public static void main(String[] args) {
        String str="010101100";
        String swap = swap(str);
        System.out.println(swap);
    }

    public static  String  swap(String str) {
        char[] chars = str.toCharArray();

        int i = 0;
        int j = chars.length - 1;
        while (i < j) {

            while (i < j && chars[i] == '0') {
                i++;
            }
            while (i < j && chars[j] == '1') {
                j--;
            }
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;

        }
        return new String(chars);

    }
}
