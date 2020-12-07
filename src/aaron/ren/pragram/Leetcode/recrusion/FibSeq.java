package aaron.ren.pragram.Leetcode.recrusion;

/**
 * 斐波那契额数字的非递归实现
 */
public class FibSeq {
    public static int FeiBo1(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("the param is less than 0");
        }
        if (n == 0 || n == 1) {
            return n;
        }
        /*int []res={0,1};
        if(n<2)
            return res[n];*/
        int result = 0;
        int fb0 = 0;
        int fb1 = 1;

        for (int i = 2; i <= n; i++) {
            result = fb1 + fb0;
            fb0 = fb1;//先把下一个元素赋值给下下个元素，如果先把result赋值给下一个元素fb1，
            //则会将fb1覆盖为result，当fb1在赋值给fb0时此时的fb0仍然是result的值
            fb1 = result;

        }
        return result;
    }

    public static void main(String[] args) {
        //测试前10个
        for (int i = 0; i < 10; i++) {
            System.out.print(FeiBo1(i) + " ");
        }

    }
}
