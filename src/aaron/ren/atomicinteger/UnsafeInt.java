package aaron.ren.atomicinteger;
/**
 * 非线程安全的++操作
 */
public class UnsafeInt {

    static int count = 0;
    public static void main(String[] args) throws Exception{
        Thread[] threads = new Thread[100];
        for(int i = 0 ;i < threads.length;i++){
            threads[i] = new Thread(){
                public void run() {
                    for(int i = 0;i < 10000;i++){
                        count++;
                    }
                }
            };
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        /*
         * 输出结果理论上是1000000,而实际结果总是小于该值
         * 造成的原因是:执行顺序不确定性以及中断的不可预知性产生的数据不一致
         */
        System.out.println(count);
    }
}
