package aaron.ren.pattern.iterator;

public interface CustomIterator <E> {

    boolean hasNext();

    E next();

    default void remove() {
        throw new UnsupportedOperationException();
    }

}