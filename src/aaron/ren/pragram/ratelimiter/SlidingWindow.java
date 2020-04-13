package aaron.ren.pragram.ratelimiter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * �������ڡ��ô���ͬ����key�����ǵ��̼߳��㡣
 *
 * @author wuweifeng wrote on 2019-12-04.
 */
public class SlidingWindow {
    /**
     * ѭ�����У�����װ��������ã���������windowSize��2��
     */
    private AtomicInteger[] timeSlices;
    /**
     * ���е��ܳ���
     */
    private int timeSliceSize;
    /**
     * ÿ��ʱ��Ƭ��ʱ�����Ժ���Ϊ��λ
     */
    private int timeMillisPerSlice;
    /**
     * ���ж��ٸ�ʱ��Ƭ�������ڳ��ȣ�
     */
    private int windowSize;
    /**
     * ��һ������������������ͨ���������ֵ
     */
    private int threshold;
    /**
     * �û�������ʼ����ʱ�䣬Ҳ���ǵ�һ������
     */
    private long beginTimestamp;
    /**
     * ���һ�����ݵ�ʱ���
     */
    private long lastAddTimestamp;

    public static void main(String[] args) {
        //1��һ��ʱ��Ƭ�����ڹ�5��
        SlidingWindow window = new SlidingWindow(100, 4, 8);
        for (int i = 0; i < 100; i++) {
            System.out.println(window.addCount(2));

            window.print();
            System.out.println("--------------------------");
            try {
                Thread.sleep(102);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public SlidingWindow(int duration, int threshold) {
        //����10���ӵİ�10����
        if (duration > 600) {
            duration = 600;
        }
        //Ҫ��5����̽������ģ�
        if (duration <= 5) {
            this.windowSize = 5;
            this.timeMillisPerSlice = duration * 200;
        } else {
            this.windowSize = 10;
            this.timeMillisPerSlice = duration * 100;
        }
        this.threshold = threshold;
        // ��֤�洢����������window
        this.timeSliceSize = windowSize * 2;

        reset();
    }

    public SlidingWindow(int timeMillisPerSlice, int windowSize, int threshold) {
        this.timeMillisPerSlice = timeMillisPerSlice;
        this.windowSize = windowSize;
        this.threshold = threshold;
        // ��֤�洢����������window
        this.timeSliceSize = windowSize * 2;

        reset();
    }

    /**
     * ��ʼ��
     */
    private void reset() {
        beginTimestamp = SystemClock.now();
        //���ڸ���
        AtomicInteger[] localTimeSlices = new AtomicInteger[timeSliceSize];
        for (int i = 0; i < timeSliceSize; i++) {
            localTimeSlices[i] = new AtomicInteger(0);
        }
        timeSlices = localTimeSlices;
    }

    private void print() {
        for (AtomicInteger integer : timeSlices) {
            System.out.print(integer + "-");
        }
    }

    /**
     * ���㵱ǰ���ڵ�ʱ��Ƭ��λ��
     */
    private int locationIndex() {
        long now = SystemClock.now();
        //�����ǰ��key�Ѿ�����һ����ʱ��Ƭ�ˣ���ô��ֱ�ӳ�ʼ�������ˣ�����ȥ������
        if (now - lastAddTimestamp > timeMillisPerSlice * windowSize) {
            reset();
        }

        return (int) (((now - beginTimestamp) / timeMillisPerSlice) % timeSliceSize);
    }

    /**
     * ����count������
     */
    public boolean addCount(int count) {
        //��ǰ�Լ����ڵ�λ�ã����ĸ�Сʱ�䴰
        int index = locationIndex();
        //        System.out.println("index:" + index);
        //Ȼ������Լ�ǰ��windowSize��2*windowSize֮������ݸ������
        //Ʃ��1���4�����ڣ���ô���鹲��8������
        //��ǰindexΪ5ʱ�������6��7��8��1��Ȼ���2��3��4��5�ļ��������Ǹô����ڵ��ܺ�
        clearFromIndex(index);

        int sum = 0;
        // �ڵ�ǰʱ��Ƭ�����+1
        sum += timeSlices[index].addAndGet(count);
        //����ǰ�漸��ʱ��Ƭ
        for (int i = 1; i < windowSize; i++) {
            sum += timeSlices[(index - i + timeSliceSize) % timeSliceSize].get();
        }
        System.out.println(sum + "---" + threshold);

        lastAddTimestamp = SystemClock.now();

        return sum >= threshold;
    }

    private void clearFromIndex(int index) {
        for (int i = 1; i <= windowSize; i++) {
            int j = index + i;
            if (j >= windowSize * 2) {
                j -= windowSize * 2;
            }
            timeSlices[j].set(0);
        }
    }

}

