package aaron.ren.pragram.Leetcode.math;

public class FindPrime {
    public static void main(String[] args){
    int num=0;
    for(int i=1;i<100;i++){
        for( num=2;num<=i;num++){
            if(i%num ==0){
                break;
            }
        }
        if(num== i){
            System.out.println(i);
        }
    }
}
}