package aaron.ren.pragram.swordtooffer;

import java.util.Stack;

//用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
public class StackImplQueue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    //导入数据
    public void push(int node) {
        stack1.push(node);
    }

    //弹出数据
    public int pop() {
        int res=0;
        //转移地点
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        if (!stack2.isEmpty()) {
            res = stack2.pop();
        }

        //这地方还得放进去
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        return res;
    }
}
