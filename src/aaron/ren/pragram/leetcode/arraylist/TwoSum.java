package aaron.ren.pragram.Leetcode.arraylist;

import java.util.HashMap;

/**
 * һ������,������ֵΪK�����
 * ���������ҵ�������������֮�͵��ڸ���������
 * 1.������� �õ�һ�����͵ڶ����� �����������бȽ�  ʱ�临�Ӷ�O(N^2)
 * 2.���ΰ������е����ַ��뵽Map��(���뵽key��)  Ȼ�����Map �ٱ����� �鿴Map���Ƿ����target-key��Ԫ��
 *
 */
public class TwoSum {
    public int[] twoSum1(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        throw new IllegalArgumentException("There is not such two integer!");
    }
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            int leave=target-nums[i];
            if(map.containsKey(leave)){
                return new int[] {map.get(leave),i};
            }
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("There is not such two integer!");
    }
}
