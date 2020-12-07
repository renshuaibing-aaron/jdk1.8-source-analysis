package aaron.ren.pragram.Leetcode.dynamic;

/**
 * 7
 * 3 8
 * 8 1 0
 * 2 7 4 4
 * 4 5 2 6 5
 */
public class MaxDistance {

    public static void main(String[] args) {
        int[][] map= {
                {7},
                {3,8},
                {8,1,0},
                {2,7,4,4},
                {4,5,2,6,5}
        };

        int solve = solve(map);
        System.out.println(solve);

        int n = map.length;
        int[][] flag = new int[n][n];
        System.out.println(solve2(map, flag, 0, 0));
    }



    /**
     * 递推计算
     *
     * @param map
     * @return
     */
    static int solve(int[][] map) {
        int n = map.length;
        int[][] flag = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                if (i == n - 1) {
                    flag[i][j] = map[i][j];
                } else {
                    flag[i][j] = Math.max(flag[i + 1][j], flag[i + 1][j + 1]) + map[i][j];
                }
            }
        }
        return flag[0][0];

    }

    /**
     * 记忆化搜索
     * @param map
     * @param flag
     * @param i
     * @param j
     * @return
     */
    static  int solve2(int[][] map,int[][] flag,int i,int j){
        if(flag[i][j]>0) {
            return flag[i][j];
        }
        if(i==map.length-1){
            flag[i][j] = map[i][j];
        }else{
            flag[i][j] = Math.max(solve2(map, flag, i+1, j), solve2(map, flag, i+1, j+1))+map[i][j];
        }
        return flag[i][j];
    }

}