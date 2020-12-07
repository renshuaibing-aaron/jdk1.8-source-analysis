package aaron.ren.pragram.Leetcode.arraylist;

/**
 * ����һ��������������������飬��Ҫ�ҵ������е��������������ǵĺ͵��ڸ�����Ŀ��ֵ��Ȼ�󷵻����ǵ��±ꡣ��Ŀ��������������������ֻ��һ���⣬����ͬһ��Ԫ�ز���ʹ�����Σ����ⷵ�ص��±�Ҫ��1��ʼ��
 * ����Ҳ������Ƿ���[-1,-1]
 */
public class GetTwoNumSum {
    //Time:O(n),Space:O(1)
    public int[] getTwoNumSumToGivenValue(int[] numbers,int target) {
        int i = 0, j = numbers.length - 1;
        while(i < j) {
            if(numbers[i] + numbers[j] > target) {
                --j;
            } else if(numbers[i] + numbers[j] < target) {
                ++i;
            } else {
                return  new int[]{i + 1, j + 1};
            }
        }
        return new int[] {-1,-1};
    }
}
