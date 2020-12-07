package aaron.ren.pragram.bytedancer.leetcode.doublepointer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IsPalindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }
    public static  boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();

        List<Character> list=new ArrayList<>();
        for(char item:chars){
            if(Character.isLetterOrDigit(item)){
                list.add(Character.toUpperCase(item));
            }
        }

        System.out.println(list);
        Object[] objects = list.toArray();
        Stack<Character> satck=new Stack<>();

        for(int i=0;i<objects.length;i++){
            satck.push((Character) objects[i]);
        }

        for(int i=0;i<objects.length;i++){
            if(!satck.isEmpty()){
                Character pop = satck.pop();
                char char1=pop;
                char char2= (char) objects[i];
                if(char1!=char2){
                    return false;
                }
            }
        }

        return true;

    }
}
