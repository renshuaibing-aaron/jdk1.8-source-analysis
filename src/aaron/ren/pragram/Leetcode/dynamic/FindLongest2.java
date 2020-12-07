package aaron.ren.pragram.Leetcode.dynamic;

/**
 * 最长递增子序列
 * https://blog.csdn.net/qq_19446965/article/details/81609837
 */
public class FindLongest2 {


    public static int findLongest2(int[] A) {
        int n = A.length;
        int[] f = new int[n];// 用于存放f(i)值；
        f[0] = 1;// 以第a1为末元素的最长递增子序列长度为1；
        int maxLen = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++)// 循环n-1次
        {
            f[i] = 1;// f[i]的最小值为1；
            for (int j = 0; j < i; j++)// 循环i 次
            {
                if (A[j] < A[i] && f[j] + 1 > f[i]) {
                    f[i] = f[j] + 1;// 更新f[i]的值。
                    maxLen = Math.max(maxLen, f[i]);
                }
            }
        }

        return maxLen;
    }
}
