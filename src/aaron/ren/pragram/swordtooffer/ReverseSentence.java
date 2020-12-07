package aaron.ren.pragram.swordtooffer;

/**
 *剑指Offer - 反转单词顺序(Java实现)
 * https://blog.csdn.net/justlikeu777/article/details/85778504?utm_medium=distribute.pc_relevant.none-task-blog-baidujs-1
 */
public class ReverseSentence {
    public String ReverseSentence(String str) {
        if(str == null || str.trim().equals("")){
            return str;
        }
        char[] ch = str.toCharArray();
        reverseCharArray(ch,0,ch.length-1);
        int start = 0;
        int end = 0;
        while(start<ch.length){
            if(ch[start]==' '){
                start++;
                end++;
            }else if(ch[end] == ' '){
                reverseCharArray(ch,start,end-1);
                start = ++end;
            }else if(end == ch.length-1){
                reverseCharArray(ch,start,end);
                start = ++end;
            }else{
                end++;
            }
        }
        return String.valueOf(ch);
    }
    public void reverseCharArray(char[] ch,int start,int end){
        while(start<end){
            char temp = ch[start];
            ch[start] = ch[end];
            ch[end] = temp;
            start++;
            end--;
        }
    }
    public String ReverseSentence2(String str) {
        if(str == null || str.trim().equals("")){
            return str;
        }
        String[] arr = str.split(" ");
        StringBuffer sb = new StringBuffer();
        for(int i = arr.length-1 ; i >0 ; i--){
            sb.append(arr[i]+" ");
        }
        sb.append(arr[0]);
        return sb.toString();
    }
}
