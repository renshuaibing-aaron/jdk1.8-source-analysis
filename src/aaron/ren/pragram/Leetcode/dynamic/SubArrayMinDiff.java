package aaron.ren.pragram.Leetcode.dynamic;

import java.util.Scanner;

/**
 * �����Ϊ�����֣�ʹ����������С
 * https://blog.csdn.net/study_000/article/details/77100395
 *
 *��������
 */
public class SubArrayMinDiff {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt() / 1024;
        }
        System.out.println(getTwoSubArrayMinDiff(a) * 1024);
    }

    public static int getTwoSubArrayMinDiff(int[] arr) {
        int sum=0;
        for(int i=0;i<arr.length;i++)
        {
            sum+=arr[i];
        }

        int temp[][]=new int[arr.length+1][sum/2+1];//ע�⿪���������������Ҫ��1,�Ǵӵ�1�е�1�п�ʼ��������
        for(int i=0;i<arr.length;i++) {
            for(int capacity=1;capacity<=sum/2;capacity++)
            {
                temp[i+1][capacity]=temp[i][capacity];
                if(arr[i]<=capacity && temp[i][capacity-arr[i]] +arr[i]>temp[i][capacity]){
                    temp[i+1][capacity]=temp[i][capacity-arr[i]]+arr[i];//���Էţ���ֵ��֮ǰҪ�������
                }
            }
        }
        return Math.max(temp[arr.length][sum/2], sum-temp[arr.length][sum/2]);
    }
}
