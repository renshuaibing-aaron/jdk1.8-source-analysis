package aaron.ren.pragram.swordtooffer;

import java.util.Arrays;

/**
 * �����������һ����λָ�����ѭ�����ƣ�ROL���������и��򵥵����񣬾������ַ���ģ�����ָ���������������һ���������ַ�����S���������ѭ������Kλ������������
 * ���磬�ַ�����S=��abcXYZdef��,Ҫ�����ѭ������3λ��Ľ��������XYZdefabc�����ǲ��Ǻܼ򵥣�OK���㶨����
 */
public class LeftRotateString {

    public static void main(String[] args) {
        System.out.println(LeftRotateString("abcdefg", 3));  // d e f g a b c
    }
    public static  String LeftRotateString(String str,int n) {

        if(str.length()==0){
            return  str;
        }
        int real = n % str.length();//i  ������ 1, n-1
        if(n==0) return str;
        char[] chars = str.toCharArray();
        char[] charsnew = new char[str.length()];
        //ʵ���ƶ��ľ���
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
