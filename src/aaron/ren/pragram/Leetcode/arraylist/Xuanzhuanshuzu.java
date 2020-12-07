package aaron.ren.pragram.Leetcode.arraylist;

/**
 * 旋转数组的查找
 * https://www.cnblogs.com/Elaine-DWL/p/11218725.html
 *
 * 递增数组[1,2,3,4,5,6,7,8]，变化为[6,7,8,1,2,3,4,5]， 给定2，判断是否存在，并返回其索引位置
 */
public class Xuanzhuanshuzu {

    public static void main(String[] args) {

        int[] nums={6,7,8,1,2,3,4,5};
        System.out.println(SearchRotatedArray(nums, 9,8));
    }


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
