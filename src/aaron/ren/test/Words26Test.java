package aaron.ren.test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author С������վ
 * @create 2019-06-24 9:51
 **/
public class Words26Test {

    //ʵ�ֹ�ƽ�������ν����ӡ26��ĸ
    public static ReentrantLock lock = new ReentrantLock(true);

    //����26��ĸ
    public static String[] words = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    public static volatile int wordIndex = 0;

    public static void main(String args[]) {
        //���������̣߳������Ԫ����ĸ��A�߳����������Ǹ�����ĸ��B�߳����
        TA a = new TA();
        Thread tA = new Thread(a);
        TB b = new TB();
        Thread tB = new Thread(b);
        //������ʱ������CPUʱ���Ƭ��Դ��һ��˭�������������趨��ʼ��ĸ�ж�
        if (words.length <= 0) {
            System.out.println("���������飬���鲻����Ϊ�գ�");
            return;
        }
        tA.start();
        tB.start();

    }

    private static boolean isYuanWord(String word) {
        if ("a".equals(word)) {
            return true;
        } else if ("e".equals(word)) {
            return true;
        } else if ("i".equals(word)) {
            return true;
        } else if ("o".equals(word)) {
            return true;
        } else if ("u".equals(word)) {
            return true;
        } else {
            return false;
        }
    }

    private static class TA implements Runnable {

        @Override
        public void run() {
            wordIndex = 0;
            while (wordIndex < words.length) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
                //�ȼ���

                lock.lock();
                System.out.println("�����߳�TA:��������");
                //ִ���߳�TAֻ���Ԫ����ĸ��������ĸ������ѭ��
                //ѭ������
                try {
                    for (; wordIndex < words.length; wordIndex++) {
                        if (isYuanWord(words[wordIndex])) {
                            System.out.println("�����߳�TA:�����Ԫ����ĸΪ��" + words[wordIndex]);
                        } else {
                            break;
                        }
                    }
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                    System.out.println("�����߳�TA:�ͷ���");
                }
            }
        }
    }


    private static class TB implements Runnable {

        @Override
        public void run() {

            wordIndex = 0;
            while (wordIndex < words.length) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
                //�ȼ���
                lock.lock();
                System.out.println("�����߳�TB:��������");
                //ִ���߳�TAֻ���Ԫ����ĸ��������ĸ������ѭ��
                //ѭ������
                try {
                    for (; wordIndex < words.length; wordIndex++) {
                        if (!isYuanWord(words[wordIndex])) {
                            System.out.println("�����߳�TB:����ĸ�����ĸΪ��" + words[wordIndex]);
                        } else {
                            break;
                        }
                    }
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                    System.out.println("�����߳�TB:�ͷ���");
                }
            }
        }
    }
}
