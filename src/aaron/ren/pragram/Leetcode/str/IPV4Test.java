package aaron.ren.pragram.Leetcode.str;


import java.util.Scanner;

public class IPV4Test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入一个IPV4地址");
        String ipStr = in.nextLine();
        System.out.println(isIPV4(ipStr));
    }

    private static Boolean isIPV4(String ipStr) {

        String[] array = ipStr.split("\\.");
        if (array.length != 4) {
            return false;
        }
        for (String str : array) {
            int num = Integer.parseInt(str);
            if (num < 0 || num > 255) {
                return false;
            }
        }
        return true;
    }
}
