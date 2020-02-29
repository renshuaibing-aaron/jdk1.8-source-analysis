package aaron.ren.forkjoin;

import java.util.concurrent.RecursiveTask;

public class CountRecursiveTask extends RecursiveTask<Integer> {
    //�ﵽ������ֱ�Ӽ������ֵ
    private int Th = 15;

    private int start;
    private int end;

    public CountRecursiveTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (this.end - this.start < Th) {
            //���С����ֵ��ֱ�ӵ�����С����ļ��㷽��
            return count();
        } else {
            //fork 2 tasks:Th = 15
            //����Դ�����ֵ����������Ϊ2�������񣬷ֱ����fork������
            //������Ը���������n��������
            int middle = (end + start) / 2;
            CountRecursiveTask left = new CountRecursiveTask(start, middle);
            System.out.println("start:" + start + ";middle:" + middle + ";end:" + end);
            left.fork();
            CountRecursiveTask right = new CountRecursiveTask(middle + 1, end);
            right.fork();
            //���һ��Ҫ�ǵ�fork()���(�����Ҫ����Ļ�)
            return left.join() + right.join();
        }
    }

    private int count() {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }
}

