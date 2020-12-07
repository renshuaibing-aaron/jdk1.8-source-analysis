package aaron.ren.pragram.sort.innersort;

/**
 * �÷ǵݹ���ʵ�ֿ���
 */

import java.util.Arrays;
import java.util.Stack;

/**
 * @author yongh
 * @Description ��������ĵݹ���ǵݹ�ʵ��
 * @date 2018��9��14�� ����2:39:00
 */
public class QuickSortCircle {
    public void quickSort(int[] a) {
        if (a == null)
            return;
        qSort(a, 0, a.length - 1);
    }

    /**
     * �ݹ�
     */
    public void qSort(int[] a, int low, int high) {
        int pivot;
        if (low >= high)
            return;

        //ԭʼ�ݹ����
        // pivot = partition(a, low, high); // ������һ��Ϊ��
        // qSort(a, low, pivot - 1); // �Ե��ӱ�����
        // qSort(a, pivot + 1, high); // �Ը��ӱ�����

        // �Ż��ݹ����
        while (low < high) {
            pivot = partition(a, low, high); // ������һ��Ϊ��
            qSort(a, low, pivot - 1); // �Ե��ӱ�����
            low = pivot + 1;
        }
    }

    public void quickSort2(int[] a) {
        if (a == null)
            return;
        qSort2(a, 0, a.length - 1);
    }

    /**
     * �ǵݹ�
     */
    public void qSort2(int[] a, int low, int high) {
        int pivot;
        if (low >= high)
            return;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(low);
        stack.push(high);
        while (!stack.empty()) {
            // �ȵ���high,�ٵ���low
            high = stack.pop();
            low = stack.pop();
            pivot = partition(a, low, high);
            // ��ѹlow,��ѹhigh
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
     * ������a���±��low��high��Ԫ�أ�ѡȡ��׼Ԫ��pivotKey��
     * �������׼�ȽϵĴ�С��������Ԫ���ŵ���׼Ԫ�ص����ˡ�
     * ����ֵΪ����׼Ԫ�ص�λ��
     */
    public int partition(int[] a, int low, int high) {

        // ����ȡ��,���м�Ԫ�ط��ڵ�һ��λ��
        if (a[low] > a[high])
            swap(a, low, high);
        if (a[(low + high) / 2] > a[high])
            swap(a, (low + high) / 2, high);
        if (a[low] < a[(low + high) / 2])
            swap(a, (low + high) / 2, low);

        int pivotKey = a[low]; // �õ�һ��Ԫ����Ϊ��׼Ԫ��
        while (low < high) { // ���ཻ�����м�ɨ��
            while (low < high && a[high] >= pivotKey)
                high--;
            a[low] = a[high];
            // swap(a, low, high); //�Ȼ�׼С��Ԫ�طŵ��Ͷ�
            while (low < high && a[low] <= pivotKey)
                low++;
            a[high] = a[low];
            // swap(a, low, high); //�Ȼ�׼���Ԫ�طŵ��߶�
        }
        a[low] = pivotKey; // ���м�λ�÷Żػ�׼ֵ
        return low; // ���ػ�׼Ԫ������λ��
    }

    public void swap(int[] a, int i, int j) {
        int temp;
        temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }

    // =========���Դ���=======
    //���Ե�Ϊ�ǵݹ鷽��quickSort2()
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