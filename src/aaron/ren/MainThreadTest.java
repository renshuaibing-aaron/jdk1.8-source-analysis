package aaron.ren;

import java.util.ArrayList;
import java.util.List;

public class MainThreadTest extends  Thread {


    private static  Integer num=0;
    private static List<Integer> list=new ArrayList<>();
    @Override
    public void run() {

synchronized (MainThreadTest.class){


        while(num<5000){
            System.out.println(num++);
            if(list.contains(num)){
                System.out.println("chucuole============ "+num);
            }
            list.add(num);

        }
}
    }

    public static void main(String[] args) {
        MainThreadTest demo1=new MainThreadTest();
        MainThreadTest demo2=new MainThreadTest();
        MainThreadTest demo3=new MainThreadTest();
        MainThreadTest demo4=new MainThreadTest();
        demo1.start();
        demo2.start();
        demo3.start();
        demo4.start();

    }
}
