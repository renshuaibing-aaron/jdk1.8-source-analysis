package aaron.ren.pragram.swordtooffer;

/**
 * һֻ����һ�ο�������1��̨�ף�Ҳ��������2��������Ҳ��������n���������������һ��n����̨���ܹ��ж���������
 */
public class JumpFloorII {
    public static void main(String[] args) {
        System.out.println(jumpFloorII(3));
    }

    public static  int jumpFloorII(int target) {

        //���ȿ�����ȷ���� f(n)=f(n-1)+f(n-2)+f(n-3)+...+f(1)+f(0)
        //��ʼ����ʱ f(0)=1  f(1)=1  f(2)=f(1)+f(0)=1+1=2
        //f(3)=f(2)+f(1)+f(0)=f(1)+f(0)+f(1)+f(0)=4


        if(target==0){
            return 1;
        }
        if(target==1){
            return 1;
        }
        int res=0;
        for(int i=0;i<target;i++){
            res=res+jumpFloorII(i);
        }

        return res;
    }
}
