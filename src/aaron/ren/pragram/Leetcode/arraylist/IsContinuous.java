package aaron.ren.pragram.Leetcode.arraylist;

import java.util.Arrays;

public class IsContinuous {
    /**
     * 题目：从扑克牌中随机抽5张牌，判断是不是一个顺子， 即这5张牌是不是连续的。
     * 2～10为数字本身， A为1。 J为11、Q为12、 为13。小王可以看成任意数字。
     * @param numbers
     * @return
     */
    public static boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length != 5) {
            return false;
        }

        // 对元素进行排序
        Arrays.sort(numbers);
        int numberOfZero = 0;
        int numberOfGap = 0;
        for (int i = 0; i < numbers.length && numbers[i] == 0; i++) {
            numberOfZero++;
        }

        // 一副牌中不可能有两个以上的王
        if(numberOfZero > 2) {
            return false;
        }

        // 第一个非0元素的位置
        int small = numberOfZero;
        int big = small + 1;

        while (big < numbers.length) {
            if (numbers[small] == numbers[big]) {
                return false;
            }

            numberOfGap += (numbers[big] - numbers[small] - 1);
            small = big;
            big++;
        }

        return numberOfGap <= numberOfZero;
    }
}
