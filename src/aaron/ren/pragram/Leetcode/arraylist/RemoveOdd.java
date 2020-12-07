package aaron.ren.pragram.Leetcode.arraylist;

import java.util.Arrays;

/**
 * 把数字分成奇数偶数进行移动
 * https://blog.csdn.net/z275598733/article/details/101459629
 */
public class RemoveOdd {

    public static void main(String[] args) {
        Integer[] arr = {8, 6, 4, -3, 5, -2, -1, 0, 1, -9, 1, -1};
        removeOdd(arr);
        System.out.println(Arrays.asList(arr));

        removeOdd2(arr);
        System.out.println(Arrays.asList(arr));

    }

    /**
     * 把正数放到负数前面
     * @param arr
     */
    public static void removeOdd2(Integer[] arr) {

      int i=0;
      int j=arr.length-1;
      while(i<j){
          while(i<j&&arr[i]>0){
              i++;
          }
          while(i<j&&arr[j]<=0){
              j--;
          }

          int tmp=arr[i];
          arr[i]=arr[j];
          arr[j]=tmp;
      }


    }
    public  static int[] solution2(int[] a){
        int i=0;
        int j=a.length-1;
        int temp=a[i];
        while(i<j){
            //从后向前找到第一个奇数
            while (i<j&&a[j]%2==0){
                j--;
            }

            //将倒数第一个奇数放到第一个偶数位置
            a[i]=a[j];

            //从前向后找到第一个偶数
            while (i<j&&a[i]%2!=0){
                i++;
            }

            //将第一个偶数放到第一个奇数位置
            a[j]=a[i];
        }

        //第一个位置的元素放到奇数偶数的分界处
        a[i]=temp;
        return a;
    }
    /**
     * 把正数放到负数前面
     * @param arr
     */
    public static void removeOdd(Integer[] arr) {

        int index = -1;                   //定义指针
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0 && index == -1) {//找到最前面的负数
                index = i;
            }
            if (arr[i] >= 0 && index != -1) {//找到了紧跟其后的正数
                //交换值
                int temp = arr[index];
                arr[index] = arr[i];
                arr[i] = temp;

                i = index;                //由于index前面的负数已经与正数交换过，所以将i重置到负数的位置
                index = -1;                //将指针重置后，重复循环操作
            }
        }


    }
}
