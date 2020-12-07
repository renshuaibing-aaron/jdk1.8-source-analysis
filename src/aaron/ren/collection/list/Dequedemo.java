package aaron.ren.collection.list;

import java.util.Deque;
import java.util.LinkedList;

public class Dequedemo {
    public static void main(String[] args) {
        Deque<String> stack = new LinkedList<>();

        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());


        stack.add("1");
        stack.add("2");
        stack.add("3");
        stack.add("4");
        System.out.println(stack.poll());
        System.out.println(stack.poll());
        System.out.println(stack.poll());
        System.out.println(stack.poll());

        Deque<Character> queue = new LinkedList<>();
        String str = "niiin";
        char[] chars = str.toCharArray();
        for (char item : chars) {
            queue.offer(item);
        }

        while (!queue.isEmpty()) {
            Character first = queue.getFirst();
            Character last = queue.getLast();
            if (first.equals(last)) {
                queue.pollFirst();
                queue.pollLast();
            }
            else{
                break;
            }
        }

        System.out.println(queue.isEmpty());
    }
}
