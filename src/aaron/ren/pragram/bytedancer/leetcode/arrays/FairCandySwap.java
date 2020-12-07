package aaron.ren.pragram.bytedancer.leetcode.arrays;

public class FairCandySwap {

    //A  --x
    //B  --y

    //sumA-x+y=sumB-y+x

    //

    public int[] fairCandySwap(int[] A, int[] B) {

        int[] result=new int[2];
        int addsumA = addsum(A);
        int addsumB = addsum(B);
        int sub = addsumA - addsumB;

        for(int i=0;i<A.length;i++){
            for(int j=0;j<B.length;j++){
                int i1 = A[i] - B[j];
                if(sub==2*i1){
                    result[0]=A[i];
                    result[1]=B[j];
                }
            }
        }
        return result;
    }

    public int addsum(int[] nums){
        int result=0;
        for(int i=0;i<nums.length;i++){
            result=result+nums[i];
        }
        return  result;
    }
}
