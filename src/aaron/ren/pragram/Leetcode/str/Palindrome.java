package aaron.ren.pragram.Leetcode.str;

//�õݹ�ʵ���ж�һ���ַ����Ƿ�Ϊ���Ĵ�

import java.util.Scanner;

public class Palindrome
{   //�ж��Ƿ�Ϊ���Ĵ�   in�Ͳ��������ַ�����ֹλ��
    public static boolean isPalindrome(String s,int i,int j){
        //�ݹ�
        if(i==j||s.length()==0||s.length()==1) {
            return true;
        }
        if(s.charAt(i) == s.charAt(j))
        {
            i++;
            j--;
            return isPalindrome(s,i,j);
        }
        else {
            return false;
        }
    }
    /* return (s.charAt(i) == s.charAt(j)) && isPalindrome(s,i+1,j-1);*/

    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        System.out.println("������һ���ַ���:");
        String str = in.nextLine();
        int i = 0;
        int j = str.length() - 1;
        if(isPalindrome(str,i,j))
            System.out.println(str + "�ǻ��Ĵ�");
        else System.out.println(str + "���ǻ��Ĵ�");
    }
}