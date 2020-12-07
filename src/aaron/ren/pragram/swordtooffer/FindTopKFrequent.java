package aaron.ren.pragram.swordtooffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 */
public class FindTopKFrequent {

    public static void main(String[] args) {
        int[] nums={1,2,3,1,1,2,4,3};
        List<Integer> integers = topKFrequent(nums, 2);
        System.out.println(integers);
    }
    public static  List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res=new ArrayList<>();
        //      先用hashmap统计词频
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
        //         对map中的词频提取前K
        //         方法二：堆排序
        int []top=topK(nums2,k);
        //         找到词频对应的数(方法二)
        for(int i=0;i<k;i++){
            //            map.keySet():返回set类型 map.values():collection类型
            for(Integer key:map.keySet()){
                if(map.get(key)==top[i]) {
                    if(res.size()==k) {
                        return res;
                    }
                    res.add(key);
                    //                    去重   map更新值：put方法覆盖原先数据
                    map.put(key,0);
                }
            }
        }
        return res;
    }
    //     方法二：堆排序
    //    调整堆 递归过程
    public static void adjust(int nums[], int size, int index){
        //        调整规则：比较父节点和子节点(left,right)
        int left=2*index+1;
        int right=2*index+2;
        //       index为调整前父节点
        int min=index;
        if(left<size && nums[left]<nums[min]) {
            min=left;
        }
        if(right<size && nums[right]<nums[min]) {
            min=right;
        }
        //       交换位置
        if(min!=index){
            int temp=nums[min];
            nums[min]=nums[index];
            nums[index]=temp;
            //递归调整堆
            adjust(nums,size,min);
        }
    }
    //    建堆
    public static void buildHeap(int[] nums){
        int length=nums.length;
        //        从length/2-1开始调整
        for(int i=length/2-1;i>=0;i--){
            adjust(nums,length,i);
        }
    }
    //    查找topK
    public static int[] topK(int[] nums, int k){
        int top[]=new int[k];
        System.arraycopy(nums, 0, top, 0, k);
        //        初始化堆
        buildHeap(top);
        //        输入数据，更新堆
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
        //      先用hashmap统计词频
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
        //        对map中的词频提取前K
        //         方法一：快排
        findK(nums2, 0, nums2.length - 1, nums2.length - k);
        //         找到词频对应的数(方法一)
        for (int i = nums2.length - 1; i >= nums2.length - k; i--) {
            //          map.keySet():返回set类型 map.values():collection类型
            for (Integer key : map.keySet()) {
                if (map.get(key) == nums2[i]) {
                    if (res.size() == k) return res;
                    res.add(key);
                    //                   去重
                    map.put(key, 0);
                }
            }
        }
        return res;
    }

    // 方法一：快排 找到index为k的划分 复杂度：O(n)
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
        //         递归
        if(i==k) {
            return;
        }
        //         左边递归
        if(i>k) {
            findK(nums,left,i-1,k);
        }
        //         右边递归
        if(i<k) {
            findK(nums,i+1,right,k);
        }
    }


}
