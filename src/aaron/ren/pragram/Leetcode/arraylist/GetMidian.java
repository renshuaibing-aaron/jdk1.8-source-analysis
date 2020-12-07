package aaron.ren.pragram.Leetcode.arraylist;

import java.util.PriorityQueue;

/**
 * 求一个无序数组的中位数方法
 * https://www.cnblogs.com/shizhh/p/5746151.html
 */
public class GetMidian {
    public void sort(int arr[],int low,int high)
    {
        int l=low;
        int h=high;
        int povit=arr[low];

        while(l<h)
        {
            while(l<h&&arr[h]>=povit) h--;
            if(l<h){
                int temp=arr[h];
                arr[h]=arr[l];
                arr[l]=temp;
                l++;
            }

            while(l<h&&arr[l]<=povit) l++;

            if(l<h){
                int temp=arr[h];
                arr[h]=arr[l];
                arr[l]=temp;
                h--;
            }
        }

        if(l>low) sort(arr,low,l-1);
        if(l<high) sort(arr,l+1,high);
    }

    public static double median2(int[] array){
        if(array==null || array.length==0) return 0;
        int left = 0;
        int right = array.length-1;
        int midIndex = right >> 1;
        int index = -1;
        while(index != midIndex){
            index = partition(array, left, right);
            if(index < midIndex) left = index + 1;
            else if (index > midIndex) right = index - 1;
            else break;
        }
        return array[index];
    }

    public static int partition(int[] array, int left, int right){
        if(left > right) return -1;
        int pos = right;
        right--;
        while(left <= right){
            while(left<pos && array[left]<=array[pos]) left++;
            while(right>left && array[right]>array[pos]) right--;
            if(left >= right) break;
            swap(array, left, right);
        }

        swap(array, left, pos);
        return left;
    }

    private static void swap(int[] array, int left, int pos) {
    }

    public static double median(int[] array){
        int heapSize = array.length/2 + 1;
        PriorityQueue<Integer> heap = new PriorityQueue<>(heapSize);
        for(int i=0; i<heapSize; i++){
            heap.add(array[i]);
        }
        for(int i=heapSize; i<array.length; i++){
            if(heap.peek()<array[i]){
                heap.poll();
                heap.add(array[i]);
            }
        }
        if(array.length % 2 == 1){
            return (double)heap.peek();
        }
        else{
            return (double)(heap.poll()+heap.peek())/2.0;
        }
    }
}
