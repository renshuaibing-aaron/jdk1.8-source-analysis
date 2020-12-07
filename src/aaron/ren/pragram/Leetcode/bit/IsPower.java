package aaron.ren.pragram.Leetcode.bit;

public class IsPower {
    public static boolean isPower(int n) {
        if(n < 1) {
            return false;
        }
        int m = n & (n-1);
        return m == 0;
    }
}
