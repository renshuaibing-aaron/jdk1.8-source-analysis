package aaron.ren.pragram.swordtooffer;

/**
 *���ǰ�ֻ��������2��3��5��������������Ugly Number����
 * �󰴴�С�����˳��ĵ�1500������������6��8���ǳ�������14���ǣ���Ϊ����������7��ϰ�������ǰ�1������һ������
 */
public class GetUglyNumber {
    public static void main(String[] args) {
        int uglyNumber = getUglyNumber(3);
        System.out.println(uglyNumber);
    }

    public static int getUglyNumber(int index) {
        if (index <= 0) {
            return 0;
        }

        int number = 0;
        int uglyCount = 0;

        while (uglyCount < index) {
            number++;

            if (isUgly(number)) {
                uglyCount++;
            }
        }

        return number;
    }

    private static boolean isUgly(int number) {
        while (number % 2 == 0) {
            number /= 2;
        }

        while (number % 3 == 0) {
            number /= 3;
        }

        while (number % 5 == 0) {
            number /= 5;
        }

        return number == 1 ? true : false;
    }

    public int getUglyNumber2(int index) {
        if (index <= 0) {
            return 0;
        }

        int[] uglyNumbers = new int[index];
        uglyNumbers[0] = 1;
        int nextUglyIndex = 1;

        int multiply2 = 0;
        int multiply3 = 0;
        int multiply5 = 0;
        int min = 0;

        while (nextUglyIndex < index) {
            min = min(uglyNumbers[multiply2] * 2, uglyNumbers[multiply3] * 3, uglyNumbers[multiply5] * 5);
            uglyNumbers[nextUglyIndex] = min;

            while (uglyNumbers[multiply2] * 2 <= uglyNumbers[nextUglyIndex]) {
                multiply2++;
            }

            while (uglyNumbers[multiply3] * 3 <= uglyNumbers[nextUglyIndex]) {
                multiply3++;
            }

            while (uglyNumbers[multiply5] * 5 <= uglyNumbers[nextUglyIndex]) {
                multiply5++;
            }

            nextUglyIndex++;
        }

        int result = uglyNumbers[index - 1];
        uglyNumbers = null;

        return result;
    }

    private int min(int num1, int num2, int num3) {
        int min = num1 < num2 ? num1 : num2;
        min = min < num3 ? min : num3;

        return min;
    }
}
