package aaron.ren.pragram.swordtooffer;

/**
 * Լɪ������ �ؼ�������ҳ���ѧ��ʽ
 * https://blog.csdn.net/littlehaes/article/details/94760713
 */
public class LastRemaining {
    public int lastRemaining(int n, int m) {
        if(n==1) {
            return 0;
        }
        return (lastRemaining(n-1, m) + m) % n;
    }
}
