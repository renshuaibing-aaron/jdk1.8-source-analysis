package aaron.ren.pragram.Leetcode.leetcodesources;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * ��εõ�һ���������е���λ����������������ж�����������ֵ����ô��λ������������ֵ����֮��λ���м����ֵ��
 * ������������ж���ż������ֵ����ô��λ������������ֵ����֮���м���������ƽ��ֵ������ʹ��Insert()������ȡ��������
 * ʹ��GetMedian()������ȡ��ǰ��ȡ���ݵ���λ����
 *
 * ����  ţ�Ƶķ�����
 * ����һ���󶥶Ѻ�С������������⡣���������ݵ�����Ϊ����ʱ�������ȷ���С���ѣ��ٴ�С�����ó��Ѷ�Ԫ�ط���󶥶��У�
 * ��Ϊż��ʱ�������ȷ���󶥶ѣ��ٴӴ󶥶����ó��Ѷ�Ԫ�ط���С�����С���֤С���ѵ���Сֵ�ȴ󶥶ѵ����ֵҪ��
 * �������ʱ��������Ϊ����ʱ��ֱ�����С���ѵĶѶ�Ԫ�أ���Ϊż��ʱ������󶥶Ѻ�С���ѵĶѶ�Ԫ�ص�ƽ��ֵ
 */
public class StreamMedian {
    public void Insert(Integer num){

        //��ʵ��������ı��� �Ǳ�֤�������м��ֵ����λ��
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
