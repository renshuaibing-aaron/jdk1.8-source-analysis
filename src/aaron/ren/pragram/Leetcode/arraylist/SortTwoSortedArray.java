package aaron.ren.pragram.Leetcode.arraylist;

import java.util.Arrays;

/**
 * 对两个有序的数组进行 归并排序 并且删除重复的数据
 */
public class SortTwoSortedArray {
    public static void main(String[] args) {
        int[] a = {12, 32, 63, 84, 105};
        int[] b = {12, 32, 53, 74, 95};
        int length1 = a.length;
        int length2 = b.length;
        int newArrayLength = length1 + length2;
        int[] result = new int[newArrayLength];
        int i = 0, j = 0, k = 0;   //i:用于标示a数组    j：用来标示b数组  k：用来标示传入的数组

        while (i < length1 && j < length2) {
            /* 元素不管重复与否，直接给合并到一起 */
            //if (a[i] <= b[j]) {
            //    result[k++] = a[i++];
            //} else {
            //    result[k++] = b[j++];
            //}

            /* 去重复元素，但是空间利用率还是浪费啦，看结果后面有默认的2个0显示 */
            if (a[i] < b[j]) {
                result[k++] = a[i++];
            } else if (a[i] == b[j]) {
                result[k++] = a[i];
                //在某个位置上2个值相等的话，取哪个都一样，
                // 然后这个相等的位置的2个值都可以不用比啦，都直接向后移动1，继续比较
                j++;
                i++;
            } else {
                result[k++] = b[j++];
            }
        }

        /* 后面while循环是用来保证两个数组比较完之后剩下的一个数组里的元素能顺利传入结果数组 */
        while (i < a.length) {
            result[k++] = a[i++];
        }

        while (j < b.length) {
            result[k++] = b[j++];
        }

        System.out.println(Arrays.toString(result));
    }
}
