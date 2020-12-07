package aaron.ren.pragram.Leetcode.str;

//用递归实现判断一个字符串是否为回文串

import java.util.Scanner;

public class Palindrome
{   //判断是否为回文串   in型参数代表字符串起止位置
    public static boolean isPalindrome(String s,int i,int j){
        //递归
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
        System.out.println("请输入一个字符串:");
        String str = in.nextLine();
        int i = 0;
        int j = str.length() - 1;
        if(isPalindrome(str,i,j))
            System.out.println(str + "是回文串");
        else System.out.println(str + "不是回文串");
    }
}