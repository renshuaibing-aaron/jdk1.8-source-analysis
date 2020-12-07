package aaron.ren.pragram.Leetcode.code;

import java.util.Stack;

/**
 * ʹ��ջ��ʵ�ֶ���
 * ջ������
 */
public class Stack2Queue {

    /**
     * ��������ջ��ʵ�ֶ���
     * ջA ���������Ԫ��
     * ջB �����Ƴ���Ԫ��
     */
    private Stack<Integer> stackA = new Stack<>();
    private Stack<Integer> stackB = new Stack<>();

    /**
     * ��Ӳ���
     * @Param element
     */
    public void enQueue(int element){
        stackA.push(element);
    }

    /**
     *
     * ���Ӳ���
     */
    public Integer deQueue(){
        if (stackB.isEmpty()){
            if (stackA.isEmpty()){
                return null;
            }
            fetchFormStackA();
        }

        return stackB.pop();
    }

    /**
     * ��stackAջ���õ���ջԪ��ѹ��ջB
     */
    private void fetchFormStackA() {
        while (!stackA.isEmpty()){
            stackB.push(stackA.pop());
        }
    }

    public static void main(String[] args) {
        Stack2Queue stackQueue = new Stack2Queue();
        stackQueue.enQueue(1);
        stackQueue.enQueue(2);
        stackQueue.enQueue(3);

        System.out.println(stackQueue.deQueue());
        System.out.println(stackQueue.deQueue());
        System.out.println(stackQueue.deQueue());

        stackQueue.enQueue(4);
        System.out.println(stackQueue.deQueue());


    }
}