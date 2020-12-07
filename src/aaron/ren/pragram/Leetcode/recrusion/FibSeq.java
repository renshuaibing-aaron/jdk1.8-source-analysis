package aaron.ren.pragram.Leetcode.recrusion;

/**
 * 쳲����������ֵķǵݹ�ʵ��
 */
public class FibSeq {
    public static int FeiBo1(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("the param is less than 0");
        }
        if (n == 0 || n == 1) {
            return n;
        }
        /*int []res={0,1};
        if(n<2)
            return res[n];*/
        int result = 0;
        int fb0 = 0;
        int fb1 = 1;

        for (int i = 2; i <= n; i++) {
            result = fb1 + fb0;
            fb0 = fb1;//�Ȱ���һ��Ԫ�ظ�ֵ�����¸�Ԫ�أ�����Ȱ�result��ֵ����һ��Ԫ��fb1��
            //��Ὣfb1����Ϊresult����fb1�ڸ�ֵ��fb0ʱ��ʱ��fb0��Ȼ��result��ֵ
            fb1 = result;

        }
        return result;
    }

    public static void main(String[] args) {
        //����ǰ10��
        for (int i = 0; i < 10; i++) {
            System.out.print(FeiBo1(i) + " ");
        }

    }
}
