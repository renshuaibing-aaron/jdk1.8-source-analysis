package aaron.ren.pragram.swordtooffer;

/**
 * ��ת�����в�����Сֵ
 * ��{3,4,5,1,2}Ϊ{1,2,3,4,5}��һ����ת�����������СֵΪ1
 */
public class MinNumberInRotateArray {


    public static void main(String[] args) {
        int[] nums={7,8,9,1,2,3,4,5,6};
        int[] nums2={4,5,6,7,8,9,1,2,3};
        System.out.println(minNumberInRotateArray(nums));
        System.out.println(minNumberInRotateArray(nums2));
    }
    public static  int minNumberInRotateArray(int[] array) {

        if (array.length == 0) {
            return 0;
        }
        if (array.length == 1) {
            return array[0];
        }

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return array[i + 1];
            } else {

                if (i == array.length - 2) {
                    return array[0];
                }

            }
        }

        return 0;
    }
}
