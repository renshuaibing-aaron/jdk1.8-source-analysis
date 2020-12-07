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

            //�Զ���ʵ��
            //Ĭ�ϵ�compare����������
            //���ʵ�ֽ��� ��Ҫ����ģ��
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
