package aaron.ren.pragram.Leetcode.dynamic;

/**
 * �����м���Ӳ�ң������������ޡ����ҳ��ܹ����ĳ����Ŀ��������ʹ�����ٵ�Ӳ���������缸��Ӳ��Ϊ[1, 3, 5], ��ֵ2������Ӳ����Ϊ2(1, 1),
 * f(0)=0
 * f(1)=1=
 * f(2)=2
 * f(3)=1
 * f(4)=2
 */
public class DynamicCoin {

    public static void main(String[] args) {

        int[] coins = {1, 3, 5};
        System.out.println(coinDynamic(coins, 0));
        System.out.println(coinDynamic(coins, 1));
        System.out.println(coinDynamic(coins, 2));
        System.out.println(coinDynamic(coins, 3));
        System.out.println(coinDynamic(coins, 4));
        System.out.println(coinDynamic(coins, 5));
        System.out.println(coinDynamic(coins, 6));
    }

    /**
     * ���ö�̬�滮
     * @param coins
     * @param k
     * @return
     */
    public static int coinDynamic(int[] coins, int k) {


        int length = coins.length;
        int[] result = new int[k + 1];

        //��ֵ
        result[0] = 0;
        int i, j;
        //��С�������
        for (i = 1; i <= k; i++) {
            //��ֵ
            result[i] = Integer.MAX_VALUE;
            for (j = 0; j < length; j++) {
                //�߽� ʲô  ����߽� ���Ը�������Ķ�̬���̻�ȡ
                if (i >= coins[j] && result[i - coins[j]] != Integer.MAX_VALUE) {
                    //����ط������
                    //f(x)=min{f(x-1)+1,f(x-3)+1,f(x-5)+1}
                    result[i] = Math.min(result[i - coins[j]] + 1, result[i]);
                }
            }


        }
        if (result[k] == Integer.MAX_VALUE) {
            result[k] = -1;
        }
        return result[k];
    }

    public static int backTrackingCoin(int[] coins, int k) {//���ݷ�+��̬�滮
        if (coins == null || coins.length == 0 || k < 1) {
            return 0;
        }
        int[][] matrix = new int[k + 1][coins.length + 1];
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < coins.length; j++) {
                int preK = i - coins[j];
                if (preK > -1) {//ֻ���ڲ�С��0ʱ, preK���ܴ���������matrix��, ���ܹ����л���.
                    matrix[i][j] = matrix[preK][coins.length] + 1;//��ֵi�ڽ��л���
                    if (matrix[i][coins.length] == 0 || matrix[i][j] < matrix[i][coins.length]) {//�����ǰ��Ӳ����Ŀ�����ٵ�, ����min�е�����Ӳ����Ŀ
                        matrix[i][coins.length] = matrix[i][j];
                    }
                }
            }
        }
        return matrix[k][coins.length];
    }
}
