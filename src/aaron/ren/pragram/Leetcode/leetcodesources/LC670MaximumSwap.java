package aaron.ren.pragram.Leetcode.leetcodesources;

public class LC670MaximumSwap {
    public int maximumSwap(int num) {
        if (num <= 10) {
            return num;
        }
        //ת���ַ����������㴦��
        char[] strArr = String.valueOf(num).toCharArray();
        char max = '0';
        int[] dp = new int[strArr.length];//dp[i]�洢 i ������������֡�
        dp[strArr.length - 1] = strArr.length - 1;

        for (int i = strArr.length - 1; i >= 0; i--) {
            if (max < strArr[i]) { // ��ѡ����ڡ���ֻ�б����������н������壩
                max = strArr[i];
                dp[i] = i;
            } else {
                dp[i] = dp[i-1];
            }
        }

        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > i && strArr[i] != strArr[dp[i]]) {
                char tmp = strArr[i];
                strArr[i] = strArr[dp[i]];
                strArr[dp[i]] = tmp;
                break; //���Ǽӣ�����bug.
            }
        }
        return Integer.valueOf(new String(strArr));
    }

}
