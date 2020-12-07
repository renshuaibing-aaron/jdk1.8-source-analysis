package aaron.ren.pragram.Leetcode.dynamic;

/**
 * ֻ�������»������ң�������ϵ����µ���̾��룬��̬�滮��
 * 1 3 1      0,0  0,1  0,2
 * 1 5 1      1,0  1,1  1,2
 * 4 2 1      2,0  2,1  1,2
 */
public class MinPathSum {
    public static void main(String[] args) {
        int[][] a = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(minPathSum(a));
    }


    /**
     * ֻ�������»������ң�������ϵ����µ���̾��룬��̬�滮��
     * 1 3 1      0,0  0,1  0,2
     * 1 5 1      1,0  1,1  1,2
     * 4 2 1      2,0  2,1  1,2
     */
    public static int minPathSum(int[][] grid) {
        if(grid==null || grid.length==0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] p = new int[m][n];
        //��С������б���
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    p[i][j] = grid[i][j];  //��һ��

                } else if (i == 0 && j > 0) {
                    p[i][j] = p[i][j-1] + grid[i][j]; //��һ��Ԫ��

                } else if (i > 0 && j == 0) {
                    p[i][j] = p[i-1][j] + grid[i][j]; //��һ��Ԫ��
                } else {
                    //״̬���� ÿһ���ڵ��ֵ���������� �����������Сֵ�����
                    p[i][j] = Math.min(p[i-1][j], p[i][j-1]) + grid[i][j];
                }
            }
        }

        return p[m-1][n-1];
    }
}
