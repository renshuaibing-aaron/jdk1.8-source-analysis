package aaron.ren.pragram.stack;

import java.util.Stack;

/**
 * ����ջ������ ��������Ƿ�Ϸ�
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
            System.out.println("�ɶ�");
        }else {
            System.out.println("���ɶ�");
        }
    }
}