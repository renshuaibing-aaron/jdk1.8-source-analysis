package aaron.ren.collection.copyonwritearrayListtest;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList/CopyOnWriteArraySet:"写入并复制"
 *
 * 注意：添加操作多时，效率低。因为每次添加时，都会进行一次复制。开销会非常的大！
 * 并发迭代多时，采用这种方法，能够提高效率。
 */
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {

        HelloThread ht = new HelloThread();

        for (int i = 0; i < 10 ; i++) {
            new Thread(ht).start();
        }
    }


    static class HelloThread implements Runnable {

        //    private static List<String> list = Collections.synchronizedList(new ArrayList<String>());

        private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        static {
            list.add("AA");
            list.add("BB");
            list.add("CC");
        }

        @Override
        public void run() {

            Iterator<String> it = list.iterator();

            while(it.hasNext()) {
                System.out.println(it.next());

                list.add("AA");
            }
        }
    }
}

