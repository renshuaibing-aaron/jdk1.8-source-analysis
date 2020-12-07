package aaron.ren.pragram.Leetcode.arraylist;

import java.util.Arrays;

/**
 * ������������������ �鲢���� ����ɾ���ظ�������
 */
public class SortTwoSortedArray {
    public static void main(String[] args) {
        int[] a = {12, 32, 63, 84, 105};
        int[] b = {12, 32, 53, 74, 95};
        int length1 = a.length;
        int length2 = b.length;
        int newArrayLength = length1 + length2;
        int[] result = new int[newArrayLength];
        int i = 0, j = 0, k = 0;   //i:���ڱ�ʾa����    j��������ʾb����  k��������ʾ���������

        while (i < length1 && j < length2) {
            /* Ԫ�ز����ظ����ֱ�Ӹ��ϲ���һ�� */
            //if (a[i] <= b[j]) {
            //    result[k++] = a[i++];
            //} else {
            //    result[k++] = b[j++];
            //}

            /* ȥ�ظ�Ԫ�أ����ǿռ������ʻ����˷����������������Ĭ�ϵ�2��0��ʾ */
            if (a[i] < b[j]) {
                result[k++] = a[i++];
            } else if (a[i] == b[j]) {
                result[k++] = a[i];
                //��ĳ��λ����2��ֵ��ȵĻ���ȡ�ĸ���һ����
                // Ȼ�������ȵ�λ�õ�2��ֵ�����Բ��ñ�������ֱ������ƶ�1�������Ƚ�
                j++;
                i++;
            } else {
                result[k++] = b[j++];
            }
        }

        /* ����whileѭ����������֤��������Ƚ���֮��ʣ�µ�һ���������Ԫ����˳������������ */
        while (i < a.length) {
            result[k++] = a[i++];
        }

        while (j < b.length) {
            result[k++] = b[j++];
        }

        System.out.println(Arrays.toString(result));
    }
}
