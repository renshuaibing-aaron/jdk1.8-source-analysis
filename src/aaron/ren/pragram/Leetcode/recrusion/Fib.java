package aaron.ren.pragram.Leetcode.recrusion;

/**
 * ������Ȿ������쳲��������У�����ֻ��һ��̨�ף���ôֻ��һ���������Ǿ���һ����һ����f(1)=1�����������̨�ף���ô��������������һ��������һ����һ�����ڶ���������һ��������,f(2)=2������д���2����n��̨�ף���ô�����һ����һ��̨�ף�ʣ�»���n-1��̨�ף���f(n-1)�������������һ����2��̨�ף�ʣ��n-2��̨�ף���f(n-2)����������ͱ�ʾf(n)=f(n-1)+f(n-2)��
 * �������쳲��������д�����΢��һ�¾��Ǳ���Ĵ𰸡���������һ�´����ʵ�֡�
 */
public class Fib {
    static final int s = 100; //�Զ����̨����

    static int compute(int stair){
        if ( stair <= 0){
            return 0;
        }
        if (stair == 1){
            return 1;
        }
        if (stair == 2){
            return 2;
        }
        return compute(stair-1) + compute(stair-2);
    }

    public static void main(String args[]) {
        System.out.println("����" + compute(s) + "���߷�");
    }
}
