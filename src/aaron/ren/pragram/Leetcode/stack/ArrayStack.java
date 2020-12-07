package aaron.ren.pragram.Leetcode.stack;

/**
 * 利用数组实现栈
 */
public class ArrayStack

{
    public static void main(String[] args){
        ArrayStack stack=new ArrayStack(3);
        stack.push(1);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        // System.out.println(queue.pop());
    }
    private Integer arr[];
    private Integer index;
    public  ArrayStack(int initSize){
        if(initSize<0){
            throw new IllegalArgumentException("initSize must more than 0");
        }
        arr=new Integer[initSize];
        index=0;
    }
    public Integer peek(){
        if(index==0)
            return null;
        return arr[index-1];
    }
    public void push(int val){
        if(index==arr.length){
            throw new ArrayIndexOutOfBoundsException("The stack is full");
        }
        arr[index++]=val;
    }
    public Integer pop(){
        if(index==0){
            throw new ArrayIndexOutOfBoundsException("The stack is empty");
        }
        return arr[--index];
    }
}
