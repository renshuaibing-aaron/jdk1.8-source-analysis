package aaron.ren.pragram.stack;

import java.util.Stack;

/**
 * 利用栈的特性 解决括号是否合法
 */
public class Check {
    public static void main(String[] args) {
        String s="(())";
        Stack<Character> sc = new Stack<Character>();

        char[] c = s.toCharArray();

        for (int i = 0; i < c.length; i++) {
            if (c[i]=='(') {
                sc.push(c[i]);
            }
            else if (c[i]==')') {
                if (sc.peek()=='(') {
                    sc.pop();
                }
            }
        }

        if (sc.empty()) {
            System.out.println("成对");
        }else {
            System.out.println("不成对");
        }
    }
}