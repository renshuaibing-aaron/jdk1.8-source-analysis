package aaron.ren.atomicinteger;
/**
 * ���̰߳�ȫ��++����
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
         * ��������������1000000,��ʵ�ʽ������С�ڸ�ֵ
         * ��ɵ�ԭ����:ִ��˳��ȷ�����Լ��жϵĲ���Ԥ֪�Բ��������ݲ�һ��
         */
        System.out.println(count);
    }
}
