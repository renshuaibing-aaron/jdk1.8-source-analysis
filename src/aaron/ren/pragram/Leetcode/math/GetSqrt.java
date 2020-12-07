package aaron.ren.pragram.Leetcode.math;

/**
 * ������������ ʵ��ƽ��������
 */
public class GetSqrt {

    private static  double EPSILON = 0.0000000001;

    public static void main(String[] args) {
        System.out.println(getSqrt());
    }

    public static  double getSqrt(){
        double low = 1.4, high = 1.5;
        double mid = (low + high) / 2;

        while (high - low > EPSILON) {
            if (mid * mid > 2) {
                high = mid;
            } else {
                low = mid;
            }
            mid = (high + low) / 2;
        }

        return mid;
    }
}
