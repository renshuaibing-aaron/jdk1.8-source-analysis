package aaron.ren.pragram.Leetcode.dynamic;

/**
 * https://blog.csdn.net/program_developer/article/details/83592202
 *【动态规划】求数组不相邻元素之和最大
 */
public class GetMaxAdd {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] arr = {1, 2, 4, 1, 7, 8, 3};
        System.out.println(dp_opt(arr));
    }
    /**
     * 递归解法
     * @param arr
     * @param i
     * @return
     */
    public static int rec_opt(int[] arr, int i) {
        if(i == 0)
            return arr[0];
        else if (i == 1)
            return Math.max(arr[0], arr[1]);
        else {
            int a = rec_opt(arr, i-2) + arr[i];
            int b = rec_opt(arr, i-1);
            return Math.max(a, b);
        }
    }

    /**
     * 动态规划解法
     * @param arr
     * @return
     */
    public static int dp_opt(int[] arr) {
        int[] opt = new int[arr.length];
        opt[0] = arr[0];
        opt[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < arr.length; i++) {
            int a = opt[i - 2] + arr[i];
            int b = opt[i - 1];
            opt[i] = Math.max(a, b);
        }
        return opt[arr.length - 1];
    }
}
