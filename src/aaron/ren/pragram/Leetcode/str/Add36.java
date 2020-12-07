package aaron.ren.pragram.Leetcode.str;

import java.util.Arrays;
import java.util.List;

/**
 * 36进制的加法 怎么实现
 * 学习Java 中的string的实现
 * https://blog.csdn.net/qq_41591147/article/details/88106843
 */
public class Add36 {
    static Character[] nums = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z' };
    static List<Character> list = Arrays.asList(nums);

    public static void main(String[] args) {
        String solution = solution("1a", "e");  //36+10  14   =60 =1o
        System.out.println(solution);
    }

    static String solution(String str1, String str2) {
        char[] s1 = str1.toCharArray();// '1' 'a'
        char[] s2 = str2.toCharArray(); //'e'
        int i = s1.length - 1; //1
        int j = s2.length - 1; //0
        int temp = 0;// 进位
        StringBuilder sb = new StringBuilder();
        while (i >= 0 && j >= 0) {
            char c1 = s1[i]; // a
            char c2 = s2[j]; // e
            int index1 = list.indexOf(c1); //10
            int index2 = list.indexOf(c2); //14
            int sum = index1 + index2 + temp;
            if (sum >= 36) {
                temp = 1;
                sb.append(list.get(sum % 36));
            } else {
                temp=0;
                sb.append(list.get(sum));
            }
            i--;
            j--;
        }
        while (i >= 0) {
            int sum = list.indexOf(s1[i]) + temp;
            if (sum >=36) {
                temp = 1;
                sb.append(list.get(sum % 36));
            } else {
                temp=0;
                sb.append(list.get(sum));
            }
            i--;
        }
        while (j >= 0) {
            int sum = list.indexOf(s2[j]) + temp;
            if (sum >=36) {
                temp = 1;
                sb.append(list.get(sum % 36));
            } else {
                temp=0;
                sb.append(list.get(sum));
            }
            j--;
        }
        if(temp!=0){
            sb.append('1');
        }
        return sb.reverse().toString();
    }
}