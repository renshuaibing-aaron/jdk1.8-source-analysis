package aaron.ren.pragram.swordtooffer;

/**
 * ��Ŀ����
 * ����һ������A[0,1,...,n-1],�빹��һ������B[0,1,...,n-1],����B�е�Ԫ��B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]������ʹ�ó�����
 * ��ע�⣺�涨B[0] = A[1] * A[2] * ... * A[n-1]��B[n-1] = A[0] * A[1] * ... * A[n-2];��
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
