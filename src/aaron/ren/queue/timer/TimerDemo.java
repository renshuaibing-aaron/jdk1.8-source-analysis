package aaron.ren.queue.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ��ʱִ������
 *   ����и��ܴ��ȱ���� ��ʱ�����ִ�� �����õ��ǵ��߳�  Ч��̫��
 */
public class TimerDemo {
    public static void main(String[] args) {
        Timer timer = new Timer(); // 1. ����Timerʵ���������̲߳�����daemon(�ػ�/��̨)�߳�
        FooTimerTask fooTimerTask = new FooTimerTask(); // 2. �����������
        timer.schedule(fooTimerTask, 3000L, 2000L); // 3. ͨ��Timer��ʱ��Ƶ�ʵ���fooTimerTask��ҵ�����
    }


    static class FooTimerTask extends TimerTask {
        @Override
        public void run() {
            // TODO ҵ�����......
            System.out.println("Hello Timer!");
        }
    }
}
