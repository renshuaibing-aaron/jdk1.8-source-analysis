package aaron.ren.pragram.Leetcode.str;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 在字符串中找出连续最长的数字串
 * 先把字符串中的字母都变为空格，用split方法按照空格拼接成一个数组，把这个数组遍历，拿出来每个值
 * 设置一个初始值 max=0，认为这个max是最大的，数组里每一个的length()都跟这个max比较，如果比max大，就赋值给max
 */
public class FindLongestNum {
    public static void main(String[] args) {

        String str ="abcd12345ed125ss123058789"	;
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            List<String> list = new ArrayList<String>();
            String s =	sc.nextLine();
            String s1= s.replaceAll("[a-z]", " ");
            //System.out.print("ppp"+s1);
            String[] s2 =s1.split(" ");
            //System.out.print(s2.length);
            for(int i=0;i<s2.length;i++) {
                if(s2[i].length()!=0) {
                    if(s2[i].charAt(0)!=' ') {
                        list.add(s2[i]);
                    }
                }
            }

            //System.out.print(list.size());
            List<Integer> index=new ArrayList<Integer>();
            int max=0;
            for (int k=1;k<list.size();k++) {
                if(list.get(k).length()>list.get(max).length()) {
                    max=k;
                    index.clear();
                }else if(list.get(k).length()==list.get(max).length()) {
                    index.add(k);
                }
            }
            index.add(max);

            System.out.println("最大的数包括： ");
            for(int i = 0;i<index.size();i++){
                System.out.println(list.get(index.get(i))+" ");
            }
            System.out.println();
        }
    }
}
