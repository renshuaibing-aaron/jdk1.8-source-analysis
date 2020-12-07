package aaron.ren.pragram.Leetcode.arraylist;

import java.util.Arrays;

/**
 * �����ֳַ�����ż�������ƶ�
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
     * �������ŵ�����ǰ��
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
            //�Ӻ���ǰ�ҵ���һ������
            while (i<j&&a[j]%2==0){
                j--;
            }

            //��������һ�������ŵ���һ��ż��λ��
            a[i]=a[j];

            //��ǰ����ҵ���һ��ż��
            while (i<j&&a[i]%2!=0){
                i++;
            }

            //����һ��ż���ŵ���һ������λ��
            a[j]=a[i];
        }

        //��һ��λ�õ�Ԫ�طŵ�����ż���ķֽ紦
        a[i]=temp;
        return a;
    }
    /**
     * �������ŵ�����ǰ��
     * @param arr
     */
    public static void removeOdd(Integer[] arr) {

        int index = -1;                   //����ָ��
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0 && index == -1) {//�ҵ���ǰ��ĸ���
                index = i;
            }
            if (arr[i] >= 0 && index != -1) {//�ҵ��˽�����������
                //����ֵ
                int temp = arr[index];
                arr[index] = arr[i];
                arr[i] = temp;

                i = index;                //����indexǰ��ĸ����Ѿ������������������Խ�i���õ�������λ��
                index = -1;                //��ָ�����ú��ظ�ѭ������
            }
        }


    }
}
