package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 * �����һ��ջ�����˳���ջ֧�ֵ�pop��push�������⣬��֧��min�������ú�������ջԪ���е���Сֵ��ִ��push��pop��min������ʱ�临�Ӷȱ���ΪO(1)��
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
