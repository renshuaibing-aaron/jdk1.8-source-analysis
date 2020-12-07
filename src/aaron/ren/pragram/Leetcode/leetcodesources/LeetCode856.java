package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 * 括号的分数
 * 给定一个平衡括号字符串?S，按下述规则计算该字符串的分数：
 *
 * () 得 1 分。
 * AB 得?A + B?分，其中 A 和 B 是平衡括号字符串。
 * (A) 得?2 * A?分，其中 A 是平衡括号字符串。
 */
public class LeetCode856 {
    public int scoreOfParentheses(String S) {
        return F(S, 0, S.length());
    }

    public int F(String S, int i, int j) {
        //Score of balanced string S[i:j]
        int ans = 0, bal = 0;

        // Split string into primitives
        for (int k = i; k < j; ++k) {
            bal += S.charAt(k) == '(' ? 1 : -1;
            if (bal == 0) {
                if (k - i == 1) {
                    ans++;
                } else {
                    ans += 2 * F(S, i+1, k);
                }
                i = k+1;
            }
        }

        return ans;
    }

}
