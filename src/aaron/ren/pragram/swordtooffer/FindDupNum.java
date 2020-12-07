package aaron.ren.pragram.swordtooffer;

/**
 * ��һ������Ϊn����������������ֶ��� 0~n-1�ķ�Χ�ڡ�
 * ������ĳЩ�������ظ��ģ�����֪���м����������ظ��ģ�Ҳ��֪��ÿ�������ظ��˼���
 * �����ظ�������
 */
public class FindDupNum {

    public static void main(String[] args) {
        int[] arr={2,3,1,5,4,6,7};
        quickSort(arr,0,6);

        for(int i=0;i<arr.length;i++){
            if(arr[i]==arr[i+1]){
                System.out.println(arr[i]);
                break;
            }
        }
    }
    /**
     * ��������
     * @param arr
     */
    public static  void quickSort(int[] arr,int start,int end){
        if(start>=end){
            return;
        }
        int temp=arr[start];
        while(start<end){

            while(start<end&&arr[end]>=temp){
                end--;
            }
            arr[start]=arr[end];

            while(start<end&&arr[start]<=temp){
                start++;
            }
            arr[end]=arr[start];
        }
        arr[start]=temp;
        quickSort(arr,0,start-1);
        quickSort(arr,start+1,end);
    }

}
