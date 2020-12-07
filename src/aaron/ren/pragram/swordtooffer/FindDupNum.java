package aaron.ren.pragram.swordtooffer;

/**
 * 在一个长度为n的数组里的所有数字都在 0~n-1的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字是重复的，也不知道每个数字重复了几次
 * 查找重复的数字
 */
public class FindDupNum {

    public static void main(String[] args) {
        int[] arr={2,3,1,5,4,6,7};
        quickSort(arr,0,6);

        for(int i=0;i<arr.length;i++){
            if(arr[i]==arr[i+1]){
                System.out.println(arr[i]);
                break;
            }
        }
    }
    /**
     * 快速排序
     * @param arr
     */
    public static  void quickSort(int[] arr,int start,int end){
        if(start>=end){
            return;
        }
        int temp=arr[start];
        while(start<end){

            while(start<end&&arr[end]>=temp){
                end--;
            }
            arr[start]=arr[end];

            while(start<end&&arr[start]<=temp){
                start++;
            }
            arr[end]=arr[start];
        }
        arr[start]=temp;
        quickSort(arr,0,start-1);
        quickSort(arr,start+1,end);
    }

}
