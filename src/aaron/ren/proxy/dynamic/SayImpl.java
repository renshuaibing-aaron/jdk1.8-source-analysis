package aaron.ren.proxy.dynamic;

public class SayImpl implements Say {
    @Override
    public void sayHello(String words) {
        System.out.println("hello:" + words);
        write();
    }

    @Override
    public void write() {
        System.out.println("writeqqwq:");


    }


}