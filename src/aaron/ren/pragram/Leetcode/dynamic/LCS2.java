package aaron.ren.pragram.Leetcode.dynamic;

import java.util.Scanner;

/**
 * 求两个字符串的最长公共子串
 * https://blog.csdn.net/ten_sory/article/details/79857531
 */
public class LCS2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String b = sc.nextLine();
        int[] dp=new int[b.length()+1];
        for(int i=0;i<dp.length;i++) {
            dp[i]=0;
        }
        int res=0,index=1;
        for(int i=0;i<a.length();i++) {
            for(int j=index;j<dp.length;j++) {
                if(a.charAt(i)==b.charAt(j-1)) {
                    dp[j]+=dp[j-1]+1;
                    res=Math.max(res, dp[j]);
                    index=++j;
                    break;
                }else{
                    for(int k=1;k<b.length();k++) {
                        dp[k]=0;
                    }
                    index=1;
                }
            }
        }

        System.out.println(res);
    }
}
