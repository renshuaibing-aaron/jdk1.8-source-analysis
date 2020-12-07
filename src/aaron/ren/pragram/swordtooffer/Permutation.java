package aaron.ren.pragram.swordtooffer;

import java.util.ArrayList;

public class Permutation {

    public static void main(String[] args) {

    }


    public static ArrayList<String> permutation(String str) {
        char[] chars = str.toCharArray();

        ArrayList<String> result= new ArrayList<>();
        dfs(result,new String(),chars,new boolean[chars.length]);
        return  result;

    }

    public static void dfs(ArrayList<String> result, String temp, char[] chars, boolean[] used) {
        if (temp.length() == chars.length) {
            //ע�ⲻ��ֱ�����temp
            result.add(temp); //��һ�����ϻ������ʼ��ArrayList al = new ArrayList(a);//aΪ���ϻ�����
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (!used[i]) {//��¼ĳ���±�����Ƿ�ʹ�ù�
                temp=temp+(chars[i]);
                used[i] = true;
                dfs(result, temp, chars, used);
                temp= temp.substring(0,temp.length()-1);
                used[i] = false;
            }
        }

    }
}