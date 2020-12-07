package aaron.ren.pragram.Leetcode.arraylist;

import java.util.Arrays;

/**
 *将给定非负整数列表中的数字排列成最大数字的2种方法。例如，给定[50，2，1,9]，最大数字为95021。
 */
public class MaxPailie {
    /**
     * 1.方法一：判断每个数的长度，通过在后面补0的方式使所有的数位数相等,
     * 比较大小排序,得到最大数。
     */
    public static void firstMeans(){
        Integer[] num=new Integer[]{51,9,370,82,4,796};
        String[] str=new String[num.length];

        int n=5;//设定待使用数组每个数字最大长度为5位;

        //长度小于5的数字循环在后面补0,直到长度为5;
        for(int k=0;k<num.length;k++){
            str[k]=num[k].toString();
            while(str[k].length()<n){
                str[k]+="0";
            }
        }
        //打印补0后按大小排序结果;
        System.out.println(Arrays.toString(str));

        //以冒泡排序的思维对数补0后，长度相同的数组元素进行排序;
        //排序过程中需要同时交换两个数组中的相对位置;
        for(int i=0;i<num.length-1;i++){
            for(int j=i+1;j<num.length;j++){
                if(Integer.parseInt(str[i])<Integer.parseInt(str[j])){
                    int temp1=num[i];
                    String temp2=str[i];
                    num[i]=num[j];
                    str[i]=str[j];
                    num[j]=temp1;
                    str[j]=temp2;
                }
            }
        }
        //打印得到最大组合数的数组;
        System.out.println("方法1："+Arrays.toString(num));
        String resulte="";
        for(int i=0;i<num.length;i++){
            resulte+=num[i];
        }
        System.out.println("最终结果_方法1："+resulte);
    }

    /**
     * 2.方法一：两两互相组合比较大小,组合方式大的数放在前面(冒泡排序思维);
     */
    public static void secondMeans(){
        Integer[] num2=new Integer[]{51,9,370,82,4,796};
        for(int i=0;i<num2.length-1;i++){
            for(int j=i+1;j<num2.length;j++){
                //数组中的数转化为字符串两两互相组合,组合结果转换为整型;
                int x=Integer.parseInt(num2[i].toString()+num2[j].toString());
                int y=Integer.parseInt(num2[j].toString()+num2[i].toString());
                //比较组合结果,组合在前的数组合结果大，就把该数交换到前面的位置;
                if(x<y){
                    int temp=num2[i];
                    num2[i]=num2[j];
                    num2[j]=temp;
                }
            }
        }
        //打印得到最大组合数的数组;
        System.out.println("方法2："+Arrays.toString(num2));
        String resulte="";
        for(int i=0;i<num2.length;i++){
            resulte+=num2[i];
        }
        System.out.println("最终结果_方法2："+resulte);
    }
}
