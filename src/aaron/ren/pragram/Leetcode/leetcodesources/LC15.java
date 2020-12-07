package aaron.ren.pragram.Leetcode.leetcodesources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ����һ������ n ������������?nums���ж�?nums?���Ƿ��������Ԫ�� a��b��c ��ʹ��?a + b + c = 0 ��
 * �����ҳ��������������Ҳ��ظ�����Ԫ�顣
 * ע�⣺���в����԰����ظ�����Ԫ�顣
 */
public class LC15 {
    public static void main(String[] args) {
        int[] num1={-1,2 ,0,1 ,4,6,5,-5};
        System.out.println(threeSum(num1));
        System.out.println(threeSum2(num1));
    }

    public static  List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>>result=new ArrayList();

        for(int i=0;i<nums.length;i++){
            for (int j=i+1;j<nums.length;j++){
                for (int k=j+1;k<nums.length;k++){
                    if((nums[i]+nums[j]+nums[k])==0){
                        boolean b = nums[i] != nums[j] && nums[i] != nums[k] && nums[k] != nums[j];
                        if(b){
                            List<Integer> list=new ArrayList();
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(nums[k]);
                            result.add(list);


                        }
                    }
                }
            }
        }

        return result;
    }

    public static  List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // ö�� a
        for (int first = 0; first < n; ++first) {
            // ��Ҫ����һ��ö�ٵ�������ͬ
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c ��Ӧ��ָ���ʼָ����������Ҷ�
            int third = n - 1;
            int target = -nums[first];
            // ö�� b
            for (int second = first + 1; second < n; ++second) {
                // ��Ҫ����һ��ö�ٵ�������ͬ
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // ��Ҫ��֤ b ��ָ���� c ��ָ������
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // ���ָ���غϣ����� b ����������
                // �Ͳ��������� a+b+c=0 ���� b<c �� c �ˣ������˳�ѭ��
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
}
