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
     * 从海量数据中查找出前k个最大值
     *
     * @param k
     * @return
     * @throws IOException
     */
    public static String findMinNumIncluedTopN(int k, String filePath) throws IOException {
        Long start = System.nanoTime();

        int[] array = new int[k];
        int index = 0;
        // 从文件导入海量数据
        BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
        String text = null;
        // 先读出前n条数据,构建堆



       PriorityQueue<Integer> minHeap = new PriorityQueue<>(5);
        do {
            text = reader.readLine();
            if (text != null) {
                array[index] = Integer.parseInt(text);
                minHeap.add(array[index]);
            }
            index++;
        } while (text != null && index <= k - 1);


        System.out.println("构建小顶堆之后:");
       /* for (int i : heap.heap) {
            System.out.print(i + " ");
        }*/
        // 遍历文件中剩余的n（文件数据容量，假设为无限大）-k条数据，如果读到的数据比heap[0]大，就替换之，同时更新堆
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
        //最后对堆进行排序(降序)

        System.out.println(Arrays.toString(minHeap.toArray()));
        return Arrays.toString(minHeap.toArray());
    }


    /**
     * 小顶堆
     */
    private static class MinHeap {
        int[] heap;
        int heapsize;

        public MinHeap(int[] array) {
            this.heap = array;
            this.heapsize = heap.length;
        }

        /**
         * 构建小顶堆
         */
        public void BuildMinHeap() {
            for (int i = heapsize / 2 - 1; i >= 0; i--) {
                Minify(i);// 依次向上将当前子树最大堆化
            }
        }

        /**
         * 堆排序
         */
        public void HeapSort() {
            for (int i = 0; i < heap.length; i++) {
                // 执行n次，将每个当前最大的值放到堆末尾
                swap(heap, 0, heapsize - 1);
                heapsize--;
                Minify(0);
            }
        }

        /**
         * 对非叶节点调整
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
            if (min == i || min >= heapsize)// 如果largest等于i说明i是最大元素
                // largest超出heap范围说明不存在比i节点大的子女
                return;
            swap(heap, i, min);
            Minify(min);
        }

        private void swap(int[] heap, int i, int min) {
            int tmp = heap[i];// 交换i与largest对应的元素位置，在largest位置递归调用maxify
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







