package java.util.concurrent;

/**
 * 延迟队列元素  这里可以看到延迟队列实现了Comparable 接口
 * 说明 在用户自定义的延迟队列元素 需要实现两个方法 getDelay 和compareTo
 * getDelay--定义剩余到期时间
 * compareTo--元素的排序规则 影响元素的获取顺序
 * A mix-in style interface for marking objects that should be
 * acted upon after a given delay.
 *
 * <p>An implementation of this interface must define a
 * {@code compareTo} method that provides an ordering consistent with
 * its {@code getDelay} method.
 *
 * @since 1.5
 * @author Doug Lea
 */
public interface Delayed extends Comparable<Delayed> {

    /**
     * Returns the remaining delay associated with this object, in the
     * given time unit.
     *
     * @param unit the time unit
     * @return the remaining delay; zero or negative values indicate
     * that the delay has already elapsed
     */
    long getDelay(TimeUnit unit);
}
