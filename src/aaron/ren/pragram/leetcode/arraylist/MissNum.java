package aaron.ren.pragram.Leetcode.arraylist;

/**
 * ������δ���ֵ���С������
 * Ҫ��ʱ��ĸ��Ӷ���O(n) �ռ临��O(1)
  */
public class MissNum {
    public int missNum(int[] arr) {
        int left = 0;
        int r = arr.length;
        while (left < r) {
            if (arr[left] == left + 1) {
                left++;
            } else if (arr[left] <= left || arr[left] > r || arr[arr[left] - 1] == arr[left]) {

                arr[left] = arr[--r];
            } else {
                swap(arr, left, arr[left] - 1);//�����������ཻ��
            }
        }
        return left + 1;
    }

    public void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    public static  int missingNumber(int[] nums) {
        //������� : 0^0=0; 0^1=1; 1^1=0; a^b^b=a;
        int data = 0;
        for (int i = 0; i < nums.length; ++i) {
            //����0���0~nums.length���±�
            data ^= i;
            //�����Ԫ�أ�һ�����ֳ������Σ�����Ϊ0����������յĽ��ֵΪ����һ�ε�������Ϊȱʧ��Ԫ��
            data ^= nums[i];
        }
        data ^= nums.length;
        return data;

    }

    public static void main(String[] args) {
        int[] nums={9,8,7,6,5,3,2,1,0};
        int i = missingNumber(nums);
        System.out.println(i);
    }
}
