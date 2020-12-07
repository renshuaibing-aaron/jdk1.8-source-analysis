package aaron.ren.pragram.bytedancer.leetcode.arrays;

import java.util.HashMap;
import java.util.Map;

public class FindSpecialInteger {

    public static void main(String[] args) {
 int[] nums={1,2,2,6,6,6,6,7,10};
        System.out.println(findSpecialInteger(nums));
    }

    /*
    * 输入：arr = [1,2,2,6,6,6,6,7,10]
输出：6*/
    public static int findSpecialInteger(int[] arr) {
        int result=0;
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            Integer integer = map.get(new Integer(arr[i]));
            if(integer!=null){
                map.put(new Integer(arr[i]),++integer);

                if(integer>arr.length/4){
                    result=arr[i];
                    break;

                }
            }else{
                map.put(new Integer(arr[i]),1);
            }
        }
        return result;
    }
}
