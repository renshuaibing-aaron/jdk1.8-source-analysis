package aaron.ren.pragram.Leetcode.str;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ���ַ������ҳ�����������ִ�
 * �Ȱ��ַ����е���ĸ����Ϊ�ո���split�������տո�ƴ�ӳ�һ�����飬���������������ó���ÿ��ֵ
 * ����һ����ʼֵ max=0����Ϊ���max�����ģ�������ÿһ����length()�������max�Ƚϣ������max�󣬾͸�ֵ��max
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

            System.out.println("������������ ");
            for(int i = 0;i<index.size();i++){
                System.out.println(list.get(index.get(i))+" ");
            }
            System.out.println();
        }
    }
}
