package aaron.ren.pragram.Leetcode.str;

/**
 *  abcd  bcef
 *  最长公共子序列
 * 首先最长公共子序列 不一定是连续的
 */
public class MaxSubsequence {

    public static void main(String[] args) {
        String str1="abcd";
        String str2="bcef";
        int i = gaxSubsequence(str1, str2);
        System.out.println(i);
    }

    public static int gaxSubsequenceByRecrusioon(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        //利用递归调用
        if (text1.charAt(m - 1) == text2.charAt(n - 1)) {
            return gaxSubsequenceByRecrusioon(text1.substring(0, m - 1), text2.substring(0, n - 1)) + 1;
        } else {
            int i = gaxSubsequenceByRecrusioon(text1.substring(0, m - 1), text2.substring(0, n));
            int i1 = gaxSubsequenceByRecrusioon(text1.substring(0, m), text2.substring(0, n - 1));
            return Math.max(i, i1);
        }

    }


    public static int gaxSubsequence(String text1, String text2){
        int m = text1.length();
        int n = text2.length();
        int[][] arr = new int[m+1][n+1];
        /**
         * 根据推导公式，如果X_i==Y_j，arr[i][j]=arr[i-1][j-1],
         * 否则arr[i][j]=Max{arr[i-1][j],arr[i][j-1]}
         */
        for(int i=1; i<=m; i++){
            for (int j=1;j<=n; j++){
                if (text1.charAt(i-1)==text2.charAt(j-1)){
                    arr[i][j]=arr[i-1][j-1]+1;
                } else{
                    arr[i][j]=Math.max(arr[i-1][j],arr[i][j-1]);
                }
            }
        }

        return arr[m][n];
    }
}
