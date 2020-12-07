package aaron.ren.pragram.sort.outsort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * ����������һ���öѱȽϺ���
 * �ʵ��Ľ��� ����� С����
 * �Ӻ��������в��ҳ�ǰk�����ֵ����ȷʱ�临�Ӷ�Ϊ��K + (n - K) * lgk,�ռ临�Ӷ�Ϊ O��k��,ĿǰΪ�����㷨�������㷨
 *
 */
public class FindMinNumIncluedTopN {

    public static void main(String[] args) throws IOException {
        String filePath = "E:\\HR-code\\20190116\\code\\jdk1.8-source-analysis\\paixu.txt";
        findMinNumIncluedTopN(6, filePath);
    }

    /**
     * �Ӻ��������в��ҳ�ǰk�����ֵ
     *
     * @param k
     * @return
     * @throws IOException
     */
    public static int[] findMinNumIncluedTopN(int k, String filePath) throws IOException {
        Long start = System.nanoTime();

        int[] array = new int[k];
        int index = 0;
        // ���ļ����뺣������
        BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
        String text = null;
        // �ȶ���ǰn������,������
        do {
            text = reader.readLine();
            if (text != null) {
                array[index] = Integer.parseInt(text);
            }
            index++;
        } while (text != null && index <= k - 1);

        MinHeap heap = new MinHeap(array);//��ʼ����
      /*  for (int i : heap.heap) {
            System.out.print(i + " ");
        }*/

        heap.BuildMinHeap();//����С����
        System.out.println("����С����֮��:");
       /* for (int i : heap.heap) {
            System.out.print(i + " ");
        }*/
        // �����ļ���ʣ���n���ļ���������������Ϊ���޴�-k�����ݣ�������������ݱ�heap[0]�󣬾��滻֮��ͬʱ���¶�
        while (text != null) {
            text = reader.readLine();
            if (text != null && !"".equals(text.trim())) {
                if (Integer.parseInt(text) > heap.heap[0]) {
                    heap.heap[0] = Integer.parseInt(text);
                    heap.Minify(0);//����С����
                }
            }
        }
        //���Զѽ�������(����)
        heap.HeapSort();

        Long end = System.nanoTime();
        long time = end - start;
        System.out.println("��ʱ��" + time + "����");
        for (int i : heap.heap) {
            System.out.println(i);
        }
        return heap.heap;
    }

    /**
     * �󶥶�
     */
    private static class MaxHeap {
        int[] heap;
        int heapsize;

        public MaxHeap(int[] array) {
            this.heap = array;
            this.heapsize = heap.length;
        }

        public void BuildMaxHeap() {
            for (int i = heapsize / 2 - 1; i >= 0; i--) {
                Maxify(i);// �������Ͻ���ǰ�������ѻ�
            }
        }

        public void HeapSort() {
            for (int i = 0; i < heap.length; i++) {
                // ִ��n�Σ���ÿ����ǰ����ֵ�ŵ���ĩβ
                swap(heap, 0, heapsize - 1);
                heapsize--;
                Maxify(0);
            }
        }

        public void Maxify(int i) {
            int l = 2 * i + 1;
            int r = 2 * i + 2;
            int largest;

            if (l < heapsize && heap[l] > heap[i])
                largest = l;
            else
                largest = i;
            if (r < heapsize && heap[r] > heap[largest])
                largest = r;
            if (largest == i || largest >= heapsize)// ���largest����i˵��i�����Ԫ��
                // largest����heap��Χ˵�������ڱ�i�ڵ�����Ů
                return;
            swap(heap, i, largest);
            Maxify(largest);
        }

        private void swap(int[] heap, int i, int largest) {
            int tmp = heap[i];// ����i��largest��Ӧ��Ԫ��λ�ã���largestλ�õݹ����maxify
            heap[i] = heap[largest];
            heap[largest] = tmp;
        }

        public void IncreaseValue(int i, int val) {
            heap[i] = val;
            if (i >= heapsize || i <= 0 || heap[i] >= val)
                return;
            int p = Parent(i);
            if (heap[p] >= val)
                return;
            heap[i] = heap[p];
            IncreaseValue(p, val);
        }

        private int Parent(int i) {
            return (i - 1) / 2;
        }
    }

    /**
     * С����
     */
    private static class MinHeap {
        int[] heap;
        int heapsize;

        public MinHeap(int[] array) {
            this.heap = array;
            this.heapsize = heap.length;
        }

        /**
         * ����С����
         */
        public void BuildMinHeap() {
            for (int i = heapsize / 2 - 1; i >= 0; i--) {
                Minify(i);// �������Ͻ���ǰ�������ѻ�
            }
        }

        /**
         * ������
         */
        public void HeapSort() {
            for (int i = 0; i < heap.length; i++) {
                // ִ��n�Σ���ÿ����ǰ����ֵ�ŵ���ĩβ
                swap(heap, 0, heapsize - 1);
                heapsize--;
                Minify(0);
            }
        }

        /**
         * �Է�Ҷ�ڵ����
         *
         * @param i
         */
        public void Minify(int i) {
            int l = 2 * i + 1;
            int r = 2 * i + 2;
            int min;

            if (l < heapsize && heap[l] < heap[i]) {
                min = l;
            } else {
                min = i;
            }
            if (r < heapsize && heap[r] < heap[min]) {
                min = r;
            }
            if (min == i || min >= heapsize)// ���largest����i˵��i�����Ԫ��
                // largest����heap��Χ˵�������ڱ�i�ڵ�����Ů
                return;
            swap(heap, i, min);
            Minify(min);
        }

        private void swap(int[] heap, int i, int min) {
            int tmp = heap[i];// ����i��largest��Ӧ��Ԫ��λ�ã���largestλ�õݹ����maxify
            heap[i] = heap[min];
            heap[min] = tmp;
        }

        public void IncreaseValue(int i, int val) {
            heap[i] = val;
            if (i >= heapsize || i <= 0 || heap[i] >= val)
                return;
            int p = Parent(i);
            if (heap[p] >= val)
                return;
            heap[i] = heap[p];
            IncreaseValue(p, val);
        }

        private int Parent(int i) {
            return (i - 1) / 2;
        }

    }

}






