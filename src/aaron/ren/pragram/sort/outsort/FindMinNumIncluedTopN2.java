package aaron.ren.pragram.sort.outsort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class FindMinNumIncluedTopN2 {

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
    public static String findMinNumIncluedTopN(int k, String filePath) throws IOException {
        Long start = System.nanoTime();

        int[] array = new int[k];
        int index = 0;
        // ���ļ����뺣������
        BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
        String text = null;
        // �ȶ���ǰn������,������



       PriorityQueue<Integer> minHeap = new PriorityQueue<>(5);
        do {
            text = reader.readLine();
            if (text != null) {
                array[index] = Integer.parseInt(text);
                minHeap.add(array[index]);
            }
            index++;
        } while (text != null && index <= k - 1);


        System.out.println("����С����֮��:");
       /* for (int i : heap.heap) {
            System.out.print(i + " ");
        }*/
        // �����ļ���ʣ���n���ļ���������������Ϊ���޴�-k�����ݣ�������������ݱ�heap[0]�󣬾��滻֮��ͬʱ���¶�
        while (text != null) {
            text = reader.readLine();
            if (text != null && !"".equals(text.trim())) {
                Integer peek = minHeap.peek();
                if (Integer.parseInt(text) > peek) {
                    Integer poll = minHeap.poll();
                    minHeap.add(Integer.parseInt(text));
                }
            }
        }
        //���Զѽ�������(����)

        System.out.println(Arrays.toString(minHeap.toArray()));
        return Arrays.toString(minHeap.toArray());
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







