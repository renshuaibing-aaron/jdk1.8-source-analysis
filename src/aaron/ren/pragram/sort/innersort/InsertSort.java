package aaron.ren.pragram.sort.innersort;

import java.util.Arrays;

public class InsertSort {

    public static void main(String[] args) {
        int[] nums={5,4,3,2,1};
        insertSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static  void insertSort(int[] a){
        int length=a.length;//数组长度，将这个提取出来是为了提高速度。
        int insertNum;//要插入的数
        for(int i=1;i<length;i++){//插入的次数
            insertNum=a[i];//要插入的数
            int j=i-1;//已经排序好的序列元素个数
            while(j>=0&&a[j]>insertNum){//序列从后到前循环，将大于insertNum的数向后移动一格
                a[j+1]=a[j];//元素移动一格
                j--;
            }
            a[j+1]=insertNum;//将需要插入的数放在要插入的位置。
        }
    }
}
