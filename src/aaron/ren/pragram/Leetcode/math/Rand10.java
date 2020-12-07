package aaron.ren.pragram.Leetcode.math;

/**
 * 用0-6的随机函数构造一个0-9的随机函数
 */
public abstract class Rand10 {

   public abstract int rand7();//返回一个0-6的数
   public int rand10()
    {
        int i = rand7();//0-6
        int j = rand7();//0-6
        i = i * 7 + j; //产生一个00-66（7进制数，相当于10进制的0-48） //其中0-48出现的概率相等。
        if (i >= 40) {
            return rand10();
        } else//0-39出现的概率相等
        {
            int i1 = i % 10;
            return i1;
        }
    }
}
