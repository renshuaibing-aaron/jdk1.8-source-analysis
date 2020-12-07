package aaron.ren.pragram.Leetcode.arraylist;

/**
 * ��01��ά���������ȫΪ1�������α߳�
 */
public class MaxSquare {
    public static int maxSquare(int[][] matrix) {
        //����Ϊ����ֱ�ӷ���0
        if (matrix.length==0||matrix[0].length==0) {
            return 0;
        }
        int M = matrix.length, N = matrix[0].length, res = 0;
        int[][] dp = new int[M][N];

        //��ʼ��dp
        for (int i=0; i<M; i++) {
            if (matrix[i][0] == 1) {
                dp[i][0] = 1;
                res = 1;
            }
        }

        for (int j=0; j<N; j++) {
            if (matrix[0][j] == 1) {
                dp[0][j] = 1;
                res = 1;
            }
        }

        for (int i=1; i<M; i++) {
            for (int j=1; j<N; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}
