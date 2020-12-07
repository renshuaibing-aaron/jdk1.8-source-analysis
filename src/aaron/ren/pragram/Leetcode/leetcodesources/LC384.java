package aaron.ren.pragram.Leetcode.leetcodesources;

import java.util.Random;

/**
 *����һ��û���ظ�Ԫ�ص����顣
 * // �����ּ��� 1, 2 �� 3 ��ʼ�����顣
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 *
 * // �������� [1,2,3] �����ؽ�����κ� [1,2,3]�����з��صĸ���Ӧ����ͬ��
 * solution.shuffle();
 *
 * // �������鵽���ĳ�ʼ״̬[1,2,3]��
 * solution.reset();
 *
 * // �����������[1,2,3]���Һ�Ľ����
 * solution.shuffle();
 */
public class LC384 {
    private int[] array;
    private int[] original;

    Random rand = new Random();

    private int randRange(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private void swapAt(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public LC384(int[] nums) {
        array = nums;
        original = nums.clone();
    }

    public int[] reset() {
        array = original;
        original = original.clone();
        return original;
    }

    public int[] shuffle() {
        for (int i = 0; i < array.length; i++) {
            swapAt(i, randRange(i, array.length));
        }
        return array;
    }

}
