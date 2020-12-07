package aaron.ren.pragram.swordtooffer;

/**
 * 题目描述
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
 * （注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
 */
public class Multiply {

    public int[] multiply(int[] A) {
        int[] res=new int[A.length];
        for (int i = 0; i < A.length; i++) {
res[i]=multiplyTmp(A,i);
        }

        return res;
    }

    private int multiplyTmp(int[] a, int index) {

        int mutiply=1;
        for(int i=1;i<a.length;i++){
            if(i==index){
                continue;
            }
            mutiply=mutiply*a[i];
        }
        return mutiply;
    }
}
