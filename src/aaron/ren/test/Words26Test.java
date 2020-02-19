package aaron.ren.test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 小诚信驿站
 * @create 2019-06-24 9:51
 **/
public class Words26Test {

    //实现公平锁，依次交替打印26字母
    public static ReentrantLock lock = new ReentrantLock(true);

    //共计26字母
    public static String[] words = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    public static volatile int wordIndex = 0;

    public static void main(String args[]) {
        //声明两个线程，如果是元音字母则A线程输出，如果是辅音字母则B线程输出
        TA a = new TA();
        Thread tA = new Thread(a);
        TB b = new TB();
        Thread tB = new Thread(b);
        //启动的时候抢夺CPU时间分片资源不一定谁先抢到，所以设定初始字母判断
        if (words.length <= 0) {
            System.out.println("请完善数组，数组不能问为空！");
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
                //先加锁

                lock.lock();
                System.out.println("我是线程TA:抢夺上锁");
                //执行线程TA只输出元音字母，其他字母则跳出循环
                //循环数组
                try {
                    for (; wordIndex < words.length; wordIndex++) {
                        if (isYuanWord(words[wordIndex])) {
                            System.out.println("我是线程TA:输出的元音字母为：" + words[wordIndex]);
                        } else {
                            break;
                        }
                    }
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                    System.out.println("我是线程TA:释放锁");
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
                //先加锁
                lock.lock();
                System.out.println("我是线程TB:抢夺上锁");
                //执行线程TA只输出元音字母，其他字母则跳出循环
                //循环数组
                try {
                    for (; wordIndex < words.length; wordIndex++) {
                        if (!isYuanWord(words[wordIndex])) {
                            System.out.println("我是线程TB:输出的辅音字母为：" + words[wordIndex]);
                        } else {
                            break;
                        }
                    }
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                    System.out.println("我是线程TB:释放锁");
                }
            }
        }
    }
}
