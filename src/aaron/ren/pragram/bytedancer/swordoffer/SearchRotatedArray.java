package aaron.ren.pragram.bytedancer.swordoffer;

public class SearchRotatedArray {


    //旋转数组的查找
    //    1 2 3 4 5 6 7
    //  4 5 6 7 1 2 3  肯定是局部有序的  二分查找法的算法
    static int  SearchRotatedArray(int a[], int key, int length)
    {
        int left = 0;
        int right = length - 1;
        while(left <= right)
        {
            int mid = (left + right) / 2;
            //int mid = (left + right) >> 1;
            if(a[mid] == key)
            {
                return mid ;
            }
            else if(a[mid] < a[right])  //右半部分是递增的
            {
                if(key > a[mid] && key <= a[right]) {
                    left = mid + 1;  //key在右部分  左指针右移动
                } else {
                    right = mid - 1;  //key肯定不在右部分
                }
            }
            else     //左半部分是递增的
            {
                if(key < a[mid] && key >= a[left]) {
                    right = mid - 1;  //key在左部分 右指针左移动
                } else {
                    left = mid + 1;  //key 肯定不在左部分
                }
            }
        }
        return -1;
    }
}

