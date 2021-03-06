package aaron.ren.pattern.iterator;

public class IterateWithIterator {
    private CustomIterator elements;

    public void setContainer(CustomIterator newElements) {
        this.elements = newElements;
    }

    // 访问并且处理容器数据的方法
    public void printElemtents() {
        if (elements == null) {
            throw new NullPointerException();
        }
        // 访问list容器内的数据
        while (elements.hasNext()) {
            System.out.println(elements.next());
        }

    }

    public static void main(String[] args) {
        IterateWithIterator it = new IterateWithIterator();
        it.setContainer(new MyContainer<Byte>().createIterator());
        it.printElemtents();
    }
}
