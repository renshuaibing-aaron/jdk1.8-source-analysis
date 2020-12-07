package aaron.ren.pragram.swordtooffer;

import java.util.Arrays;

/**
 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
 * 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
 */
public class LeftRotateString {

    public static void main(String[] args) {
        System.out.println(LeftRotateString("abcdefg", 3));  // d e f g a b c
    }
    public static  String LeftRotateString(String str,int n) {

        if(str.length()==0){
            return  str;
        }
        int real = n % str.length();//i  的区间 1, n-1
        if(n==0) return str;
        char[] chars = str.toCharArray();
        char[] charsnew = new char[str.length()];
        //实际移动的距离
        for(int i=0;i<str.length();i++) {
            if(i<=real-1){
                System.out.println(chars[i]);
                System.out.println(i+real-1);
                charsnew[str.length()-real+i]=chars[i];
            }else{
                charsnew[i-real]=chars[i];
            }

        }

        return String.valueOf(charsnew);

    }
}
