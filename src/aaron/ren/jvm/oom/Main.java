package aaron.ren.jvm.oom;

public class Main {

    //-Xmx2688M -Xms2688M -Xmn960M -XX:MetaspaceSize=50M -XX:MaxMetaspaceSize=100M -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -XX:+UseConcMarkSweepGC
    public static void main(String[] args) throws Exception {
        while (true) {
            Class clazz0 = new MyClassLoader().loadClass("com.sankuai.discover.memory.OOM");
        }
    }
}
