package aaron.ren.pragram.swordtooffer;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法
 */
public class JumpFloorII {
    public static void main(String[] args) {
        System.out.println(jumpFloorII(3));
    }

    public static  int jumpFloorII(int target) {

        //首先可以明确的是 f(n)=f(n-1)+f(n-2)+f(n-3)+...+f(1)+f(0)
        //初始条件时 f(0)=1  f(1)=1  f(2)=f(1)+f(0)=1+1=2
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
