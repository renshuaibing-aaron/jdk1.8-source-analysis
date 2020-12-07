package aaron.ren.collection.list.sort;

import java.util.Arrays;
import java.util.Comparator;

public class StringSortDemo {

    public static void main(String[] args) {
        String [] stringArray={"d","c","a","b"};
        java.util.Arrays.sort(stringArray);
        for(String str :stringArray){
            System.out.println(str);
        }

        System.out.println("---------------");
        Arrays.sort(stringArray, new Comparator<String>() {

            //自定义实现
            //默认的compare方法是升序
            //如果实现降序 需要明白模板
            @Override
            public int compare(String o1, String o2) {
                int ans = o1.compareTo(o2);
                if(ans>0){
                    return -1;
                }else{
                    return 1;
                }
            }
        });

        for(String str :stringArray){
            System.out.println(str);
        }

    }

}
