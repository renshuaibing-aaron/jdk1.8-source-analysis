package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 * 请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。执行push、pop和min操作的时间复杂度必须为O(1)。
 */

import java.util.Stack;

public class LC0302 {
    int min = Integer.MAX_VALUE;
    Stack<Integer> valStack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    /** initialize your data structure here. */
    public LC0302() {

    }

    public void push(int x) {
        min = Math.min(min, x);
        valStack.push(x);
        minStack.push(min);
    }

    public void pop() {
        valStack.pop();
        minStack.pop();
        if (minStack.isEmpty()) {
            min = Integer.MAX_VALUE;
        } else {
            min = minStack.peek();
        }
    }

    public int top() {
        return valStack.peek();
    }

    public int getMin() {
        return min;
    }
}
