package aaron.ren.collection.queue;

import java.util.PriorityQueue;

public class PriorityQueueDemo {

    public static void main(String[] args) {
        PriorityQueue queue=new PriorityQueue();
        queue.add(0);
        queue.add(6);
        queue.add(1);
        queue.add(4);
        queue.add(5);
        queue.add(2);
        queue.add(3);




        while(!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }
}
