package aaron.ren.pragram.Leetcode.dynamic;

/**
 * https://blog.csdn.net/qq_41901915/article/details/104231528
 * ��һ���� 0 �� 1 ��ɵĶ�ά�����ڣ��ҵ�ֻ���� 1 ����������Σ��������������
 * ʾ��:
 * ����:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * ���: 4
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if(matrix.length<=0)
        {
            return 0;
        }
        int[][] dp=new int[matrix.length][matrix[0].length];
        int max1=0;
        int max2=0;
        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[0].length;j++)
            {
                if(i==0||j==0)
                {
                    if(matrix[i][j]=='0')
                    {
                        dp[i][j]=0;
                    }else
                    {
                        dp[i][j]=1;
                        max1=1;
                    }
                }else
                {
                    if(matrix[i][j]=='0')
                    {
                        continue;
                    }
                    dp[i][j]=Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]))+1;
                    max2=Math.max(max2,dp[i][j]);
                }
            }
        }
        return Math.max(max2*max2,max1*max1);

}
}
