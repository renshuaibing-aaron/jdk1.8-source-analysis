package aaron.ren.jvm.constantpool;

/**
 * ���������� String
 */
public class StringTest {


    public static void main(String[] args) {
        String s1=new String("abc");
        String s2=new String("abc");
        System.out.println(s1==s2) ;//����false

        String s3="abc";
        String s4="abc";
        System.out.println(s3==s4);//����true

//         ��
    }
}
