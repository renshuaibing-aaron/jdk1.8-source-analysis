package aaron.ren.pragram.Leetcode.str;

import java.util.HashMap;
import java.util.Map;

/**
 *给定一个字符串，找到最多有 k 个不同字符的最长子字符串
 * 例如，给定 s ="eceba", k = 3, T 是"eceb"，长度为4.
 */
public class LongestSubstring {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int maxLen = 0;

        // Key: letter; value: the number of occurrences.
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int i, j = 0;
        char c;
        for (i = 0; i < s.length(); i++) {
            // j 在向前移动时，统计 j 所对应的字母出现的次数，当hash表中有 k 个不同字母时， j 就无需向前移动了
            while (j < s.length()) {
                c = s.charAt(j);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                } else {
                    if (map.size() == k) {
                        break;
                    }
                    map.put(c, 1);
                }
                j++;
            }

            maxLen = Math.max(maxLen, j - i);
            // i 往前移动一位，在hash表中移除当前i对应的字母一次
            c = s.charAt(i);
            if (map.containsKey(c)){
                int count = map.get(c);
                if (count > 1) {
                    map.put(c, count - 1);
                } else {
                    map.remove(c);
                }
            }
        }
        return maxLen;
    }
}
