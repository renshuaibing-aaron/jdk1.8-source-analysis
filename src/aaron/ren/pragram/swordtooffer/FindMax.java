package aaron.ren.pragram.swordtooffer;

import java.util.Arrays;

/**
 *求连续子数组的最大和
 *最大连续子序列和
 */
public class FindMax {
    public int FindGreatestSumOfSubArray(int[] array) {
        int[] max= new int[array.length];
        max[0] = array[0];
        int maxNum = Integer.MIN_VALUE;
        for(int i = 1; i<array.length;i++){
            if(max[i-1]<0) {
                max[i] = array[i];
            } else if(max[i-1]+array[i]<=0 && max[i-1]+array[i]<array[i]){
                max[i] = array[i];
            }else{
                max[i] = max[i-1]+array[i];
            }
            if(maxNum<max[i]) {
                maxNum = max[i];
            }
        }
        return maxNum;
    }


    //最大连续子序列的和
    //{6,-3,-2,7,-15,1,2,2}
    public static  int FindGreatestSumOfSubArray2(int[] array) {
        //dp(n)=max{int[n],dp(n-1)+int[n]}

        int[] res=new int[array.length];
        res[0]=array[0];
        int max=res[0];
        for (int i=1;i<array.length;i++){
            res[i]=Math.max(array[i],res[i-1]+array[i]);
            max=Math.max(max,res[i]);
        }

        System.out.println(Arrays.toString(res));
        return max;


    }
}
