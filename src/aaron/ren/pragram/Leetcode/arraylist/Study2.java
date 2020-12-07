package aaron.ren.pragram.Leetcode.arraylist;

import java.util.Stack;

/**
 * 输出数组中每个元素以及后面第一个比它大的二元组序列--栈的应用
 */
public class Study2 {

    public static void main(String[] args) {
        printArr(new int[]{7,2,3,8,6,9,1});
    }
    public static void printArr(int[] arr){
        Stack<Integer> s = new Stack<>();
        for(int i=0; i<arr.length; i++){
            if(s.isEmpty()) s.push(arr[i]);
            else{
                while(!s.isEmpty()&&s.peek()<arr[i]){
                    System.out.println(s.pop() + " , " + arr[i]);
                }
                s.push(arr[i]);
            }
        }
        while(!s.isEmpty()){
            System.out.println(s.pop() + " , " + "-1");
        }
    }
}
