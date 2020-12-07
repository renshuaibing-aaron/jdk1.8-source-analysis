package aaron.ren.pragram.Leetcode.arraylist;

/**
 * 数组中未出现的最小正整数
 * 要求时间的复杂度是O(n) 空间复杂O(1)
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
                swap(arr, left, arr[left] - 1);//就是两个互相交换
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
        //运用异或 : 0^0=0; 0^1=1; 1^1=0; a^b^b=a;
        int data = 0;
        for (int i = 0; i < nums.length; ++i) {
            //先用0异或0~nums.length的下标
            data ^= i;
            //再异或元素，一个数字出现两次，异或后为0，而异或最终的结果值为出现一次的数，即为缺失的元素
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
