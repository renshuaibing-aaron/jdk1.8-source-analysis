package aaron.ren.pragram.Leetcode.leetcodesources;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，
 * 使用GetMedian()方法获取当前读取数据的中位数。
 *
 * 分析  牛逼的方法啊
 * 利用一个大顶堆和小顶堆来解决问题。当输入数据的总量为奇数时，数据先放入小顶堆，再从小顶堆拿出堆顶元素放入大顶堆中；
 * 当为偶数时，数据先放入大顶堆，再从大顶堆中拿出堆顶元素放入小顶堆中。保证小顶堆的最小值比大顶堆的最大值要大。
 * 输出数据时，当总量为奇数时，直接输出小顶堆的堆顶元素，当为偶数时，输出大顶堆和小顶堆的堆顶元素的平均值
 */
public class StreamMedian {
    public void Insert(Integer num){

        //其实这个方法的本质 是保证两个堆中间的值是中位数
        if(count%2 == 0){
            maxHeap.offer(num);
            int filteredMaxMum = maxHeap.poll();
            minHeap.offer(filteredMaxMum);
        }else{
            minHeap.offer(num);
            int filteredMinNum = minHeap.poll();
            maxHeap.offer(filteredMinNum);
        }
        count++;
    }

    public Double GetMedian(){
        if(count%2 == 0){
            return (minHeap.peek() + maxHeap.peek())/2.0;
        }else{
            return minHeap.peek()/1.0;
        }
    }
    private int count = 0;
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(15,new Comparator<Integer>(){

        @Override
        public int compare(Integer arg0, Integer arg1) {
            // TODO Auto-generated method stub
            return arg1-arg0;
        }

    });  //1 1 2 2 2 6 7
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        StreamMedian test = new StreamMedian();
        test.Insert(2);
        test.Insert(1);
        test.Insert(2);
        test.Insert(2);
        test.Insert(1);
        test.Insert(6);
        test.Insert(7);
        System.out.println(test.GetMedian());
    }


}
