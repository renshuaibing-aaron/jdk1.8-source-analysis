package aaron.ren.pragram.Leetcode.dynamic;

import java.util.Scanner;

//题目：求两个字符串最长的公共子序列。
//a1bc2和ab34c  最长公共子序列为 abc
//运用了动态规划
public class LongestCommonSubsequence
{
    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("请输入两个字符串：");
        String str1=sc.nextLine();
        String str2=sc.nextLine();
        System.out.println("最长公共子序列为："+lcse(str1,str2));
    }
    //
    public static String lcse(String str1,String str2) {
        if(str1 == null||str1==null||str1.equals("")||str2.equals(""))
            return "字符串不合格";
        char[] ch1=str1.toCharArray();
        char[] ch2=str2.toCharArray();
        int[][] dp=getdp(ch1,ch2);
        int m=ch1.length-1;
        int n=ch2.length-1;
        char[] res = new char[dp[m][n]];
        int index =res.length-1;
        while(index >= 0) {
            if(n>0&&dp[m][n]==dp[m][n-1]) {
                n--;
            }else if(m>0&&dp[m][n] == dp[m-1][n]) {
                m--;
            }else {
                res[index--]=ch1[m];
                m--;
                n--;
            }
        }
        return String.valueOf(res);
    }

    public static int[][] getdp(char[] ch1,char[] ch2){
        int[][] dp=new int[ch1.length][ch2.length];

        //	给dp矩阵的第一行赋值
        int m=Integer.MAX_VALUE;
        for(int i=0;i<ch2.length;i++) {
            dp[0][i]=0;
            if(ch1[0]==ch2[i])
            {
                dp[0][i]=1;
                m=i;
            }
            if(i>m) {
                dp[0][i]=1;
            }
        }
        //	给dp矩阵的第一列赋值
        m=Integer.MAX_VALUE;
        for(int i=0;i<ch2.length;i++) {
            dp[i][0]=0;
            if(ch2[0]==ch1[i])
            {
                dp[i][0]=1;
                m=i;
            }
            if(i>m) {
                dp[i][0]=1;
            }
        }
        //给dp矩阵其他地方赋值
        for(int i=1;i<ch1.length;i++)
            for(int j=1;j<ch2.length;j++)
            {
                dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                if(ch1[i]==ch2[j])
                    dp[i][j]=dp[i-1][j-1]+1;
            }

        //		打印dp矩阵，检验一下：
        for(int i=0;i<ch1.length;i++)
            for(int j=0;j<ch2.length;j++)
                System.out.print(dp[i][j]+" ");
        return dp;
    }
}
