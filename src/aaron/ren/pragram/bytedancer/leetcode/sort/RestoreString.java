package aaron.ren.pragram.bytedancer.leetcode.sort;

public class RestoreString {

    //太笨了 利用一个数组 直接填充
    public String restoreString(String s, int[] indices) {
        char[] result=new char[indices.length];
        char[] chars = s.toCharArray();
        int i=0;
        for(int item:indices){
            result[item]=chars[i];
            i++;
        }
        //这里如何显示？？？？
       // return Arrays.toString(chars);
        return String.valueOf(result);

    }
}
