package aaron.ren.pragram.swordtooffer;

import java.util.Stack;

//������ջ��ʵ��һ�����У���ɶ��е�Push��Pop������ �����е�Ԫ��Ϊint���͡�
public class StackImplQueue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    //��������
    public void push(int node) {
        stack1.push(node);
    }

    //��������
    public int pop() {
        int res=0;
        //ת�Ƶص�
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        if (!stack2.isEmpty()) {
            res = stack2.pop();
        }

        //��ط����÷Ž�ȥ
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        return res;
    }
}
