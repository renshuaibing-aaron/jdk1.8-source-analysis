package aaron.ren.pragram.swordtooffer;

/**
 * ��Ŀ����
 * дһ������������������֮�ͣ�Ҫ���ں������ڲ���ʹ��+��-��*��/����������š�
 */
public class Add {

    public static void main(String[] args) {
        int add = Add(12, 13);
        System.out.println(add);
    }

    public static  int Add(int num1,int num2) {
        while (num2!=0) {
            int temp = num1^num2;
            num2 = (num1&num2)<<1;
            num1 = temp;
        }
        return num1;
    }
}
