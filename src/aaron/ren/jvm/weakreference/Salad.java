package aaron.ren.jvm.weakreference;


import java.lang.ref.WeakReference;

/**
 * Salad class
 * �̳�WeakReference����Apple��Ϊ�����á�
 * ע�⵽ʱ����յ���Apple��������Salad
 */
public class Salad extends WeakReference<Apple> {
    public Salad(Apple apple) {
        super(apple);
    }
}
