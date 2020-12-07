package aaron.ren.pragram.swordtooffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *����һ���ǿյ��������飬�������г���Ƶ��ǰ k �ߵ�Ԫ�ء�
 * ʾ�� 1:
 * ����: nums = [1,1,1,2,2,3], k = 2
 * ���: [1,2]
 * ʾ�� 2:
 * ����: nums = [1], k = 1
 * ���: [1]
 */
public class FindTopKFrequent {

    public static void main(String[] args) {
        int[] nums={1,2,3,1,1,2,4,3};
        List<Integer> integers = topKFrequent(nums, 2);
        System.out.println(integers);
    }
    public static  List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res=new ArrayList<>();
        //      ����hashmapͳ�ƴ�Ƶ
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])) {
                map.put(nums[i],map.get(nums[i])+1);
            }
            else {
                map.put(nums[i],1);
            }
        }
        int[] nums2=new int[map.size()];
        int index=0;
        for(Integer key:map.keySet()){
            nums2[index++]=map.get(key);
        }
        //         ��map�еĴ�Ƶ��ȡǰK
        //         ��������������
        int []top=topK(nums2,k);
        //         �ҵ���Ƶ��Ӧ����(������)
        for(int i=0;i<k;i++){
            //            map.keySet():����set���� map.values():collection����
            for(Integer key:map.keySet()){
                if(map.get(key)==top[i]) {
                    if(res.size()==k) {
                        return res;
                    }
                    res.add(key);
                    //                    ȥ��   map����ֵ��put��������ԭ������
                    map.put(key,0);
                }
            }
        }
        return res;
    }
    //     ��������������
    //    ������ �ݹ����
    public static void adjust(int nums[], int size, int index){
        //        �������򣺱Ƚϸ��ڵ���ӽڵ�(left,right)
        int left=2*index+1;
        int right=2*index+2;
        //       indexΪ����ǰ���ڵ�
        int min=index;
        if(left<size && nums[left]<nums[min]) {
            min=left;
        }
        if(right<size && nums[right]<nums[min]) {
            min=right;
        }
        //       ����λ��
        if(min!=index){
            int temp=nums[min];
            nums[min]=nums[index];
            nums[index]=temp;
            //�ݹ������
            adjust(nums,size,min);
        }
    }
    //    ����
    public static void buildHeap(int[] nums){
        int length=nums.length;
        //        ��length/2-1��ʼ����
        for(int i=length/2-1;i>=0;i--){
            adjust(nums,length,i);
        }
    }
    //    ����topK
    public static int[] topK(int[] nums, int k){
        int top[]=new int[k];
        System.arraycopy(nums, 0, top, 0, k);
        //        ��ʼ����
        buildHeap(top);
        //        �������ݣ����¶�
        for(int j=k;j<nums.length;j++){
            if(nums[j]>top[0]){
                top[0]=nums[j];
                adjust(top,k,0);
            }
        }
        return top;
    }























    public List<Integer> topKFrequent2(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        //      ����hashmapͳ�ƴ�Ƶ
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        int[] nums2 = new int[map.size()];
        int index = 0;
        for (Integer key : map.keySet()) {
            nums2[index++] = map.get(key);
        }
        //        ��map�еĴ�Ƶ��ȡǰK
        //         ����һ������
        findK(nums2, 0, nums2.length - 1, nums2.length - k);
        //         �ҵ���Ƶ��Ӧ����(����һ)
        for (int i = nums2.length - 1; i >= nums2.length - k; i--) {
            //          map.keySet():����set���� map.values():collection����
            for (Integer key : map.keySet()) {
                if (map.get(key) == nums2[i]) {
                    if (res.size() == k) return res;
                    res.add(key);
                    //                   ȥ��
                    map.put(key, 0);
                }
            }
        }
        return res;
    }

    // ����һ������ �ҵ�indexΪk�Ļ��� ���Ӷȣ�O(n)
    public void findK(int[] nums,int left,int right,int k){
        int partion=nums[left];
        int i=left,j=right;
        while(i<j){
            if(nums[j]>=partion) {
                j--;
            } else {
                nums[i] = nums[j];
                nums[j] = partion;
                while(i<j) {
                    if(nums[i]<=partion) {
                        i++;
                    } else {
                        nums[j] = nums[i];
                        nums[i] = partion;
                        break;
                    }
                }
            }
        }
        //         �ݹ�
        if(i==k) {
            return;
        }
        //         ��ߵݹ�
        if(i>k) {
            findK(nums,left,i-1,k);
        }
        //         �ұߵݹ�
        if(i<k) {
            findK(nums,i+1,right,k);
        }
    }


}
