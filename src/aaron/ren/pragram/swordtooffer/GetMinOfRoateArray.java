package aaron.ren.pragram.swordtooffer;

/**
 * 旋转数组中查找最小值
 * 组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1
 * 典型的二分法查找
 * mid和left的相对大小做比较
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
