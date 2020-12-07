package aaron.ren.pragram.Leetcode.tree;

/**
 * https://www.cnblogs.com/youxin/p/3297788.html
 * ��һ������Ϊn����������������ֶ���0��n-1�ķ�Χ�ڡ� ������ĳЩ�������ظ��ģ�����֪���м����������ظ��ġ�
 * Ҳ��֪��ÿ�������ظ����Ρ����ҳ�����������һ���ظ������֡�
 * ���磬������볤��Ϊ7������{2,3,1,0,2,5,3}����ô��Ӧ������ǵ�һ���ظ�������2��
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
