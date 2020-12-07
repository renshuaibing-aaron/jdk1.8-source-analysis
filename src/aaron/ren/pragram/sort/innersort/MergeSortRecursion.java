package aaron.ren.pragram.sort.innersort;

import java.util.Arrays;

public class MergeSortRecursion {

    public static void main(String[] args) throws Exception {

        int[]  nums={5,3,1,4,2};
        System.out.println(Arrays.toString(sort(nums)));
    }


    public static int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        if (sourceArray.length < 2) {
            return sourceArray;
        }
        int middle = (int) Math.floor(sourceArray.length / 2);

        int[] left = Arrays.copyOfRange(sourceArray, 0, middle);
        int[] right = Arrays.copyOfRange(sourceArray, middle, sourceArray.length);

        return merge(sort(left), sort(right));
    }

    protected static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
    }

}
