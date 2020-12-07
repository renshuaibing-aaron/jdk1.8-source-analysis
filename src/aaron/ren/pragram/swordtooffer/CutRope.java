package aaron.ren.pragram.swordtooffer;

/**
 * ����һ������Ϊn�����ӣ�������Ӽ�����������m�Σ�m��n����������n>1����m>1��m<=n����ÿ�����ӵĳ��ȼ�Ϊk[1],...,k[m]������k[1]x...xk[m]���ܵ����˻��Ƕ��٣�
 * ���磬�����ӵĳ�����8ʱ�����ǰ������ɳ��ȷֱ�Ϊ2��3��3�����Σ���ʱ�õ������˻���18��
 */
public class CutRope {

    public static void main(String[] args) {
        System.out.println(cutRope(5));
    }
    /**
     * �ö�̬�滮ʵ��
     *
     * @param target
     * @return
     */
    public static int cutRope(int target) {
        if (target == 0) {
            return 0;
        }
        if (target == 1) {
            return 0;
        }
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        int[] result = new int[target+1]; //ע������ǳ�����Ҫ��1
        result[0] = 1;
        result[1] = 1;
        result[2] = 2;
        result[3] = 3;

        int max = 0;
        //f(n)=max{f(i)f(n-i)}
        //f(4)=max{f(1)f(3),f(2)f(2)}
        //f(5)=max{f(1)f(4),f(2)f(3)}
        //f(6)=max{f(1)f(5),f(2)f(4),f(3)f(3)}
        //f(7)=max{f(1)f(6),f(2)f(5),f(3)f(4)}

        for (int i = 4; i <= target; i++) {
            max = 0;  //����ط��������³�ʼ��
            for (int j = 1; j < i; j++) {  //����ط������Ż�
                int product = result[j] * result[i-j];
                if (max < product) {
                    max = product;
                }
                result[i] = max;
            }

        }
        max = result[target];
        return max;

    }
}
