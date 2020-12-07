package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 *累加数是一个字符串，组成它的数字可以形成累加序列。
 */
public class LC306AdditiveNumber {
    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() < 3) {
            return false;
        }

        for (int i = 1; i <= num.length() / 2; i++) {
            for (int j = 1; j <= num.length() / 2; j++) {
                if (dfs(num.substring(0, i), num.substring(i, i + j), num.substring(i + j))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(String a, String b, String c) {
        if ((a.length() > 1 && a.charAt(0) == '0') || (b.length() > 1 && b.charAt(0) == '0')) {
            return false;
        }

        String sum = getSum(a, b);
        if (sum.equals(c)) {
            return true;
        }

        if (sum.length() >= c.length() || !c.substring(0, sum.length()).equals(sum)) {
            return false;
        }

        return dfs(b, c.substring(0, sum.length()), c.substring(sum.length()));
    }

    private String getSum(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0 || carry > 0) {
            int temp = (i >= 0 ? a.charAt(i) - '0' : 0) + (j >= 0 ? b.charAt(j) - '0' : 0) + carry;
            sb.insert(0, temp % 10);
            carry = temp / 10;
            i--;
            j--;
        }

        return sb.toString();
    }
}
