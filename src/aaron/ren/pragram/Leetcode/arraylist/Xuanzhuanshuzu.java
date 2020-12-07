package aaron.ren.pragram.Leetcode.arraylist;

/**
 * ��ת����Ĳ���
 * https://www.cnblogs.com/Elaine-DWL/p/11218725.html
 *
 * ��������[1,2,3,4,5,6,7,8]���仯Ϊ[6,7,8,1,2,3,4,5]�� ����2���ж��Ƿ���ڣ�������������λ��
 */
public class Xuanzhuanshuzu {

    public static void main(String[] args) {

        int[] nums={6,7,8,1,2,3,4,5};
        System.out.println(SearchRotatedArray(nums, 9,8));
    }


    static int  SearchRotatedArray(int a[], int key, int length)
    {
        int left = 0;
        int right = length - 1;
        while(left <= right)
        {
            int mid = (left + right) / 2;
            //int mid = (left + right) >> 1;
            if(a[mid] == key)
            {
                return mid ;
            }
            else if(a[mid] < a[right])  //�Ұ벿���ǵ�����
            {
                if(key > a[mid] && key <= a[right]) {
                    left = mid + 1;  //key���Ҳ���  ��ָ�����ƶ�
                } else {
                    right = mid - 1;  //key�϶������Ҳ���
                }
            }
            else     //��벿���ǵ�����
            {
                if(key < a[mid] && key >= a[left]) {
                    right = mid - 1;  //key���󲿷� ��ָ�����ƶ�
                } else {
                    left = mid + 1;  //key �϶������󲿷�
                }
            }
        }
        return -1;
    }
}
