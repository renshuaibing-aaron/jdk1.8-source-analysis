package aaron.ren.pragram.Leetcode.math;

/**
 * ��0-6�������������һ��0-9���������
 */
public abstract class Rand10 {

   public abstract int rand7();//����һ��0-6����
   public int rand10()
    {
        int i = rand7();//0-6
        int j = rand7();//0-6
        i = i * 7 + j; //����һ��00-66��7���������൱��10���Ƶ�0-48�� //����0-48���ֵĸ�����ȡ�
        if (i >= 40) {
            return rand10();
        } else//0-39���ֵĸ������
        {
            int i1 = i % 10;
            return i1;
        }
    }
}
