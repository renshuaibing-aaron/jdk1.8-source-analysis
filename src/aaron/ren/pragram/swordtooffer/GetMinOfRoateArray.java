package aaron.ren.pragram.swordtooffer;

/**
 * ��ת�����в�����Сֵ
 * ��{3,4,5,1,2}Ϊ{1,2,3,4,5}��һ����ת�����������СֵΪ1
 * ���͵Ķ��ַ�����
 * mid��left����Դ�С���Ƚ�
 */
public class GetMinOfRoateArray {
    public static void main(String[] args) {
        int[] arr={4,5,6,7,8,9,1,2,3};
        System.out.println(minNumberInRotateArray(arr));
    }
    public static  int minNumberInRotateArray(int [] numbers) {
        int low = 0 ; int high = numbers.length - 1;
        while(low < high){
            int mid = low + (high - low) / 2;
            System.out.println("===="+mid);
            if(numbers[mid] > numbers[high]){
                low = mid + 1;
            }else if(numbers[mid] == numbers[high]){
                high = high - 1;
            }else{
                high = mid;
            }
        }
        return numbers[low];
    }
    public static int getMin(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return 0;
        }
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while (low < high) {
            mid = (low + high) / 2;
            if (low == high) {
                break;
            }
            if (arr[low] < arr[high]) {
                return arr[low];
            }
            if (arr[low] > arr[mid]) {
                high = mid;
                continue;
            }
            if (arr[mid] > arr[high]) {
                low = mid;
                continue;
            }
            while (low < high) {
                if (arr[low] == arr[mid]) {
                    low++;
                } else if (arr[low] < arr[mid]) {
                    return arr[low];
                } else {
                    high = mid;
                    break;
                }
            }
        }
        return Math.min(arr[low], arr[high]);
    }
}
