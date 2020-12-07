package aaron.ren.pragram.sort.outsort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * 处理海量数据一般用堆比较合适
 * 适当的建立 大根堆 小根堆
 * 从海量数据中查找出前k个最大值，精确时间复杂度为：K + (n - K) * lgk,空间复杂度为 O（k）,目前为所有算法中最优算法
 *
 */
public class FindMinNumIncluedTopN {

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
    public static int[] findMinNumIncluedTopN(int k, String filePath) throws IOException {
        Long start = System.nanoTime();

        int[] array = new int[k];
        int index = 0;
        // 从文件导入海量数据
        BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
        String text = null;
        // 先读出前n条数据,构建堆
        do {
            text = reader.readLine();
            if (text != null) {
                array[index] = Integer.parseInt(text);
            }
            index++;
        } while (text != null && index <= k - 1);

        MinHeap heap = new MinHeap(array);//初始化堆
      /*  for (int i : heap.heap) {
            System.out.print(i + " ");
        }*/

        heap.BuildMinHeap();//构建小顶堆
        System.out.println("构建小顶堆之后:");
       /* for (int i : heap.heap) {
            System.out.print(i + " ");
        }*/
        // 遍历文件中剩余的n（文件数据容量，假设为无限大）-k条数据，如果读到的数据比heap[0]大，就替换之，同时更新堆
        while (text != null) {
            text = reader.readLine();
            if (text != null && !"".equals(text.trim())) {
                if (Integer.parseInt(text) > heap.heap[0]) {
                    heap.heap[0] = Integer.parseInt(text);
                    heap.Minify(0);//调整小顶堆
                }
            }
        }
        //最后对堆进行排序(降序)
        heap.HeapSort();

        Long end = System.nanoTime();
        long time = end - start;
        System.out.println("用时：" + time + "纳秒");
        for (int i : heap.heap) {
            System.out.println(i);
        }
        return heap.heap;
    }

    /**
     * 大顶堆
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
                Maxify(i);// 依次向上将当前子树最大堆化
            }
        }

        public void HeapSort() {
            for (int i = 0; i < heap.length; i++) {
                // 执行n次，将每个当前最大的值放到堆末尾
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
            if (largest == i || largest >= heapsize)// 如果largest等于i说明i是最大元素
                // largest超出heap范围说明不存在比i节点大的子女
                return;
            swap(heap, i, largest);
            Maxify(largest);
        }

        private void swap(int[] heap, int i, int largest) {
            int tmp = heap[i];// 交换i与largest对应的元素位置，在largest位置递归调用maxify
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






