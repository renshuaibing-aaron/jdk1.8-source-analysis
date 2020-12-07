package aaron.ren.pragram.Leetcode.tree;

/**
 * https://www.cnblogs.com/youxin/p/3297788.html
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 */
public class Duplicate {
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        return false;
    }

    public static int  duplicate(int  [] numbers,int [] duplication) {
        if (numbers.length == 0) {
            return -1;
        }
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 0 || numbers[i] >= numbers.length) {
                return -1;
            }
        }

        for (int j = 0; j < numbers.length; j++) {
            while (numbers[j] != j) {
                if (numbers[j] == numbers[numbers[j]]) {
                    duplication[0] = numbers[j];
                    return duplication[0];
                }
                int temp = numbers[j];
                numbers[j] = numbers[temp];
                numbers[temp] = temp;
            }
        }
        return -1;
    }
}
