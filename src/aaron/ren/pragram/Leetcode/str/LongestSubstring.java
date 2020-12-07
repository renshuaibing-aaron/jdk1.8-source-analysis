package aaron.ren.pragram.Leetcode.str;

import java.util.HashMap;
import java.util.Map;

/**
 *����һ���ַ������ҵ������ k ����ͬ�ַ�������ַ���
 * ���磬���� s ="eceba", k = 3, T ��"eceb"������Ϊ4.
 */
public class LongestSubstring {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int maxLen = 0;

        // Key: letter; value: the number of occurrences.
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int i, j = 0;
        char c;
        for (i = 0; i < s.length(); i++) {
            // j ����ǰ�ƶ�ʱ��ͳ�� j ����Ӧ����ĸ���ֵĴ�������hash������ k ����ͬ��ĸʱ�� j ��������ǰ�ƶ���
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
            // i ��ǰ�ƶ�һλ����hash�����Ƴ���ǰi��Ӧ����ĸһ��
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
