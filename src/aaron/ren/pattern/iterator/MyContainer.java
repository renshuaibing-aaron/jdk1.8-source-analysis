package aaron.ren.pattern.iterator;

public class MyContainer<E> implements CusomIterable<E>{

    Object[] elements;

    public MyContainer() {
        elements = new Byte[10];
        for (int i =0; i < 10; i ++) {
            elements[i] = (byte) i;
        }
    }

    private class Itr<E> implements CustomIterator<E> {
        private int position = -1;
        private Object[] data = elements;
        @Override
        public boolean hasNext() {
            return ++ position < data.length;
        }

        @Override
        public E next() {
            return (E) data[position];
        }
    }

    @Override
    public CustomIterator<E> createIterator() {
        return new Itr();
    }

}
