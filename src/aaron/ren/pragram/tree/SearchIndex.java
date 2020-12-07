package aaron.ren.pragram.tree;

/**
 *
 */
public class SearchIndex {

    public static void main(String[] args) {
        int[]nums={1,2,3,4,5,6,7,8,8,9};
        System.out.println(searchIndex(7, nums));
    }
    public static int searchIndex(int key, int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int start = 0;
        int end = arr.length - 1;
        if (arr[start] > key || arr[end] < key) {
            return -1;
        }

        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (arr[mid] > key) {
                end = mid - 1;
            } else if (arr[mid] < key) {
                start = mid + 1;
            } else {
                //确保是第一次出现的情况
                while (mid - 1 >= 0 && arr[mid - 1] == key) {
                    mid--;
                }
                return mid;
            }
        }
        return -1;
    }

    public static int[] searchAllIndex(int key, int[] arr) {
        int[] result;
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int start = 0;
        int end = arr.length - 1;
        if (arr[start] > key || arr[end] < key) {
            return new int[0];
        }

        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (arr[mid] > key) {
                end = mid - 1;
            } else if (arr[mid] < key) {
                start = mid + 1;
            } else {
                int midL = mid;
                int midR = mid;
                int count = 1;

                while (midL - 1 >= 0 && arr[midL - 1] == key) {
                    count++;
                    midL--;
                }
                while (midR + 1 < arr.length && arr[midR + 1] == key) {
                    count++;
                    midR++;
                }
                result = new int[count];
                for (int i = 0, from = midL; i < count; i++, from++) {
                    result[i] = from;
                }
                return result;
            }
        }
        return new int[0];
    }
}
