package aaron.ren.concurrentprogramming.threadlocal;

public class Test {
    public static void main(String[] args) {

        for(int i=1;i<10;i++){
            new Thread(()->{
                ThreadLocalMap.put("ceshi", "ceshi");//存放用户的信息
            }).start();

        }
    }
}
