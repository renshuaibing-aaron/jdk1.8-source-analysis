package aaron.ren.pragram.Leetcode.recrusion;

/**
 * 打印斐波那契数列
 * 坐标 0 1 2 3 4 5 6 7
 * 数列 0 1 1 2 3 5 8 13 21
 */
public class PrintFib {
    public static void main(String[] args) {
        System.out.println(fib(10));
        System.out.println(fib2(10));

    }

    //用非递归实现  循环实现
    public static int fib2(int num) {
        int result = 0;
        if (num == 0) {
            return 0;

        } else if (num == 1) {
            return 1;
        }
        int a = 0;
        int b = 1;
        for (int i = 2; i <= num; i++) {
            result = a + b;
            a = b;
            b = result;

        }
        return result;
    }

    //用递归实现
    public static int fib(int num) {
        if (num == 0) {
            return 0;
        } else if (num == 1) {
            return 1;
        } else {
            return fib(num - 1) + fib(num - 2);
        }
    }

}
