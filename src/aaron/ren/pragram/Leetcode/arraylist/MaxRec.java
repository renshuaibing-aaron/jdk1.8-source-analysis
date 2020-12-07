package aaron.ren.pragram.Leetcode.arraylist;

/**
 * ��һ��ֻ����0��1�ľ�����ֻ����1������Ӿ����С
 */
public class MaxRec {
    public static int get(int []arr){
        int max = 0;
        for(int i=0;i<arr.length;i++){
            int left = (i==0)?0:i-1;
            int right = (i==arr.length-1)?arr.length-1:i+1;
            while (left>=0&& arr[i]<=arr[left])
                left--;
            while (right<arr.length && arr[i]<=arr[right])
                right++;
            int sum=(right-left-1)*arr[i];
            max=max>sum?max:sum;
        }
        return  max;
    }
    public  static int maxSize(int [][]arr){
        int res[]=new int[arr[0].length];
        int max=0;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                res[j]=(arr[i][j]==0) ? 0:(res[j]+1);
            }
            int sum=get(res);
            max=max>sum? max:sum;
        }
        return max;
    }
    public static void main(String args[]){
        int arr[][]={{1 ,0, 1, 0 ,0},{1 ,1 ,1 ,1 ,1},{1, 1, 1 ,1, 1},{1 ,0 ,0 ,1 ,0}};
        //ystem.out.println(Arrays.toString(maxSize(arr)));
        System.out.print(maxSize(arr));
    }
}
