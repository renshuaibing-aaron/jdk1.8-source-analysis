package aaron.ren.pragram.Leetcode.arraylist;

/**
 * 给定一个 n*n 的二维数组，数组的每行 、每列递增。现在需要找出 某个 数字 target ，如果存在则找出其位置
 */
public class fingTargetFromArr {
    public static void main(String args[]){
        int[][] arr = {
                {1,2,8,9},
                {2,4,9,12},
                {4,7,10,13},
                {6,8,11,15}
        };
        System.out.println(findTarget(arr,6));

    }

    //利用每行，每列递增的性质，饿哦们可以从二维数组左下角的 6 或者 右上角的 9 为起始点
    public static boolean findTarget(int[][] arr,int tar){
        int rows = arr.length;			//数组的 行数
        int cows = arr[0].length;		//数组的列数
        int i = 0;
        int j = cows - 1;
        while( i < rows && j >= 0){
            int temp = arr[i][j];		//中间变量
            if(temp == tar){
                System.out.println("target:" + tar +" in cows:" + (i+1) + " " +"rows:" + (j + 1));
                return true;
            }
            else
            if(temp > tar)
                j--;
            else
                i++;
        }
        System.out.println("Not Found!");
        return false;
    }

}
