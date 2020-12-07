package aaron.ren.pragram.Leetcode.str;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * https://www.jianshu.com/p/a7741619dd58
 * http://www.cxyxiaowu.com/2869.html
 *
 * cdsdfeffefddwferw
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("cdsdfeffefddwferw"));
    }

    public  static  String longestPalindrome(String s) {
        if (s.isEmpty()) {
            return s;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int left = 0;
        int right = 0;
        for (int i = n - 2; i >= 0; i--) {
            dp[i][i] = true;
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) &&( j-i<3||dp[i+1][j-1]);//小于3是因为aba一定是回文
                if(dp[i][j]&&right-left<j-i){
                    left=i;
                    right=j;
                }
            }
        }
        return s.substring(left,right+1);
    }
}
