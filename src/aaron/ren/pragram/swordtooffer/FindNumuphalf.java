package aaron.ren.pragram.swordtooffer;

/**
 * https://blog.csdn.net/u013276277/article/details/72854716
 * 找出数组中出现次数大于一半的数字 Java实现 剑指offer
 */
public class FindNumuphalf {
    private int[] data;
    public int MoreThanHalfNum_Solution(int [] array) {
        data=array;
        startSort(0,data.length-1);
        int key=data[(data.length-1)/2];
        int count=0;
        for(int i=0;i<data.length;i++){
            if(data[i]==key){
                count++;
            }
        }
        if(count>(data.length)/2)
            return key;
        else
            return 0;

    }
    public void startSort(int start,int end){
        if(start>=end) return;
        int index=partition(start,end);
        startSort(start,index-1);
        startSort(index+1,end);
    }
    public int partition(int start,int end){
        int key=data[start];
        while(start<end){
            while(data[end]>=key&&start<end){
                end--;
            }
            data[start]=data[end];
            while(data[start]<=key&&start<end){
                start++;
            }
            data[end]=data[start];
        }
        data[start]=key;
        return start;
    }
}
