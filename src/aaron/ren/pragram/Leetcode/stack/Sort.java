package aaron.ren.pragram.Leetcode.stack;

/**
 * 将栈中的元素升序排序
 */

import java.util.Stack;

public class Sort {
    Stack<Integer> sort(Stack<Integer> s) {
        Stack<Integer> r = null;
        while (!s.empty()) {
            int tmp = s.pop();
            s.pop();
            while (!r.isEmpty() && tmp < r.pop()) {
                s.push(r.pop());
                r.pop();
            }
            r.push(tmp);
        }
        return r;
    }
}
