package aaron.ren.clone;

import java.util.*;

public class CloneDemo {



    public static void main(String[] args) {
        List<Stu> list=new ArrayList<>();

        Stu stu2=null;
        for(int i=0;i<5;i++){
            list.add(stu2);
        }

        //������Կ�������list add ����ʵ�Ƕ���ĵ�ַ
        System.out.println(Arrays.toString(list.toArray()));


        Stu stu =new Stu("A");

        Stu test = test(stu);

        System.out.println(stu.name);
        System.out.println(test.name);

        System.out.println("************************");


        int a=100;
        String str="abc";
        StringBuffer buffer=new StringBuffer("def");
        change(a,str,buffer);

        System.out.println(a);
        System.out.println(str);
        System.out.println(buffer);


    }

    private static void change(int a, String str, StringBuffer buffer) {

        a=200; //��ֵ
        str="sades"; //�������Ϊ��ֵ
        buffer=new StringBuffer("dsdewdew"); //������
    }

    public static Stu test(Stu stu){
        //ɵ�ƲŻ��ڷ��������޸�
        Stu stu1=new Stu("B");
        stu=stu1;
        return stu;
    }


    private  static  class Stu{
        private String name;
        private String age;

        public Stu(String a) {
            this.name=a;
        }
    }

}
