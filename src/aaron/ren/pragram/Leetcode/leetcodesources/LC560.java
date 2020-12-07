package aaron.ren.pragram.Leetcode.leetcodesources;

import java.util.HashMap;
import java.util.Map;

/**
 * ����һ�����������һ������?k������Ҫ�ҵ��������к�Ϊ?k?��������������ĸ�����
 *
 * ʾ�� 1 :
 *
 * ����:nums = [1,1,1], k = 2
 * ���: 2 , [1,1] �� [1,1] Ϊ���ֲ�ͬ�������
 */
public class LC560 {
    public int subarraySum(int[] nums, int k) {
        /**
         ɨ��һ������, ʹ��map��¼����ͬ���ĺ͵Ĵ���, ��ÿ��i�����ۼƺ�sum���ж�map���Ƿ���sum-k
         **/
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, ret = 0;

        for(int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            if(map.containsKey(sum-k)) {
                ret += map.get(sum-k);
            }
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }

        return ret;
    }
}
