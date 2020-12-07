package aaron.ren.pragram.Leetcode.dynamic;

/**
 * �����������
 * https://blog.csdn.net/qq_19446965/article/details/81609837
 */
public class FindLongest2 {


    public static int findLongest2(int[] A) {
        int n = A.length;
        int[] f = new int[n];// ���ڴ��f(i)ֵ��
        f[0] = 1;// �Ե�a1ΪĩԪ�ص�����������г���Ϊ1��
        int maxLen = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++)// ѭ��n-1��
        {
            f[i] = 1;// f[i]����СֵΪ1��
            for (int j = 0; j < i; j++)// ѭ��i ��
            {
                if (A[j] < A[i] && f[j] + 1 > f[i]) {
                    f[i] = f[j] + 1;// ����f[i]��ֵ��
                    maxLen = Math.max(maxLen, f[i]);
                }
            }
        }

        return maxLen;
    }
}
