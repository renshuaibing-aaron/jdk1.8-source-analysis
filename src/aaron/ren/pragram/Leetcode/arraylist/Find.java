package aaron.ren.pragram.Leetcode.arraylist;

/**
 * 一个二维数组，每一列的数字从左往右增大，每一行从上往下增大，求一个指定的数字在这个数组中的位置
 * 杨氏矩阵查找
 */
public class Find {

    /**
     *
     * @param m 		矩阵行
     * @param n			矩阵列
     * @return
     */
    private int[][] getMatrix(int m,int n){
        if(m<0 ||n<0)
            return null;
        int k = 1;
        int[][] matrix = new int[m][n];
        //initial matrix
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                matrix[i][j]=k++;
            }
        }
        return matrix;
    }
    /**
     * 杨氏矩阵,定位法解决问题
     */
    public  String young(int[][] matrix,int value){
        int row = matrix.length;
        int column = matrix[0].length;
        int i =0,j=column;
        while(i<row && j>0){
            if(matrix[i][j-1]>value){
                j--;
            }
            else if(matrix[i][j-1]<value){
                i++;
            }
            else{
                return "Find it in "+(i+1)+" row and "+j+" column";
            }
        }
        return "Not Find it";
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Young Matrix");
        Find obj = new Find();
        String result = obj.young(obj.getMatrix(4,5),17);
        System.out.println(result);
    }

}

