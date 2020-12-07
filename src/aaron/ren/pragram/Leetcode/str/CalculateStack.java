package aaron.ren.pragram.Leetcode.str;

/**
 *带括号的加减乘除字符串运算 四则运算
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CalculateStack {


    public static Map pro=new HashMap();
    public static void init()
    {
        pro.put('+', 1);
        pro.put('-', 1);
        pro.put('*', 2);
        pro.put('/', 2);
    }
    public static int getIndex(String str)
    {
        int index1=(str.indexOf('+')==-1?str.length():str.indexOf('+'));
     int index2=(str.indexOf('-')==-1?str.length():str.indexOf('-'));
     int index3=(str.indexOf('*')==-1?str.length():str.indexOf('*'));
     int index4=(str.indexOf('/')==-1?str.length():str.indexOf('/'));
     int index=index1<index2?index1:index2;
     index=index<index3?index:index3;
     index=index<index4?index:index4;
     return index;
    }
    public static double cal(char op,double num1,double num2)
    {
        switch(op)
        {
   case '+':
   return num1+num2;
   case '-':
   return num1-num2;
   case '*':
   return num1*num2;
   default:
         return num1/num2;
        }
    }
    public static double fun1(String str)
    {
        init();
        Stack st1=new Stack();
        Stack st2=new Stack();
        int fop=0;
        while(str.length()>0)
        {
            int index=getIndex(str);
            st1.push(Double.parseDouble(str.substring(0,index)));
            if(index!=str.length())
            {
                char op=str.charAt(index);
                str=str.substring(index+1);
                while(true)
                {
                    if((int)pro.get(op)>fop)
                    {
                        st2.push(op);
                        fop=(int)pro.get(op);
                        break;
                    }
                    else
                    {
                        double num2= (double) st1.pop();
                        double num1=(double) st1.pop();
                        double result=cal((char)st2.pop(),num1,num2);
                        st1.push(result);
                        if(st2.size()==0)
                        {
                            st2.push(op);
                            fop=(int)pro.get(op);
                            break;
                        }
                        char cop=(char) st2.pop();
                        fop=(int)pro.get(cop);
                        st2.push(cop);
                    }
                }
     }
            else
            {
                break;
            }
        }
        while(st2.size()!=0)
        {
            double num2=(double) st1.pop();
            double num1=(double) st1.pop();
            char op=(char) st2.pop();
            st1.push(cal(op,num1,num2));
        }
        double result=(double) st1.pop();
        return result;
    }
    public static double fun2(String str)
    {
        while(str.indexOf('(')!=-1)
        {
            int left=0;
            int right=str.length();
            char op;
            for(int i=0;i<str.length();i++)
            {
                if(str.charAt(i)=='(')
                {
                    left=i;
                }
                if(str.charAt(i)==')')
                {
                    right=i;
                    break;
                }
            }
            str=str.substring(0,left)+fun1(str.substring(left+1,right))+str.substring(right+1);
        }

        return fun1(str);

    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
           String str="1.0*4*5/2+2*3+4+5-4+3*2";
           String str1="((1+2*(3+4*(2+3)))+2*(3+5))*(3+4)-33+5";
           double result=fun2(str1);
           System.out.println(result);
    }


}
