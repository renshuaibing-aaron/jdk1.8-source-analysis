package aaron.ren.pragram.Leetcode.arraylist;

/**
 * һ����ά���飬ÿһ�е����ִ�����������ÿһ�д�������������һ��ָ������������������е�λ��
 * ���Ͼ������
 */
public class Find {

    /**
     *
     * @param m 		������
     * @param n			������
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
     * ���Ͼ���,��λ���������
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

