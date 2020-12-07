package aaron.ren.pragram.sort.innersort;

/**
 * 用非递归来实现快排
 */

import java.util.Arrays;
import java.util.Stack;

/**
 * @author yongh
 * @Description 快速排序的递归与非递归实现
 * @date 2018年9月14日 下午2:39:00
 */
public class QuickSortCircle {
    public void quickSort(int[] a) {
        if (a == null)
            return;
        qSort(a, 0, a.length - 1);
    }

    /**
     * 递归
     */
    public void qSort(int[] a, int low, int high) {
        int pivot;
        if (low >= high)
            return;

        //原始递归操作
        // pivot = partition(a, low, high); // 将数列一分为二
        // qSort(a, low, pivot - 1); // 对低子表排序
        // qSort(a, pivot + 1, high); // 对高子表排序

        // 优化递归操作
        while (low < high) {
            pivot = partition(a, low, high); // 将数列一分为二
            qSort(a, low, pivot - 1); // 对低子表排序
            low = pivot + 1;
        }
    }

    public void quickSort2(int[] a) {
        if (a == null)
            return;
        qSort2(a, 0, a.length - 1);
    }

    /**
     * 非递归
     */
    public void qSort2(int[] a, int low, int high) {
        int pivot;
        if (low >= high)
            return;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(low);
        stack.push(high);
        while (!stack.empty()) {
            // 先弹出high,再弹出low
            high = stack.pop();
            low = stack.pop();
            pivot = partition(a, low, high);
            // 先压low,再压high
            if (low < pivot - 1) {
                stack.push(low);
                stack.push(pivot - 1);
            }
            if (pivot + 1 < high) {
                stack.push(pivot + 1);
                stack.push(high);
            }
        }
    }

    /**
     * 对数组a中下标从low到high的元素，选取基准元素pivotKey，
     * 根据与基准比较的大小，将各个元素排到基准元素的两端。
     * 返回值为最后基准元素的位置
     */
    public int partition(int[] a, int low, int high) {

        // 三数取中,将中间元素放在第一个位置
        if (a[low] > a[high])
            swap(a, low, high);
        if (a[(low + high) / 2] > a[high])
            swap(a, (low + high) / 2, high);
        if (a[low] < a[(low + high) / 2])
            swap(a, (low + high) / 2, low);

        int pivotKey = a[low]; // 用第一个元素作为基准元素
        while (low < high) { // 两侧交替向中间扫描
            while (low < high && a[high] >= pivotKey)
                high--;
            a[low] = a[high];
            // swap(a, low, high); //比基准小的元素放到低端
            while (low < high && a[low] <= pivotKey)
                low++;
            a[high] = a[low];
            // swap(a, low, high); //比基准大的元素放到高端
        }
        a[low] = pivotKey; // 在中间位置放回基准值
        return low; // 返回基准元素所在位置
    }

    public void swap(int[] a, int i, int j) {
        int temp;
        temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }

    // =========测试代码=======
    //测试的为非递归方法quickSort2()
    public void test1() {
        int[] a = null;
        quickSort2(a);
        System.out.println(Arrays.toString(a));
    }

    public void test2() {
        int[] a = {};
        quickSort2(a);
        System.out.println(Arrays.toString(a));
    }

    public void test3() {
        int[] a = {1};
        quickSort2(a);
        System.out.println(Arrays.toString(a));
    }

    public void test4() {
        int[] a = {3, 3, 3, 3, 3};
        quickSort2(a);
        System.out.println(Arrays.toString(a));
    }

    public void test5() {
        int[] a = {-3, 6, 3, 1, 3, 7, 5, 6, 2};
        quickSort2(a);
        System.out.println(Arrays.toString(a));
    }

}