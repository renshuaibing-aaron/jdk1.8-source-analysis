package aaron.ren.pattern.iterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IterateWithoutIterator {

    private List list;

    public void setContainer(List list) {
        this.list = list;
    }

    // ���ʲ��Ҵ����������ݵķ���
    public void printElemtents() {
        // ����list�����ڵ�����
        if (list == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < list.size(); i ++) {
            System.out.println(list.get(i));
        }
    }

    public static void main(String[] args) throws NullPointerException {
        IterateWithoutIterator it = new IterateWithoutIterator();

        List list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i ++) {
            list.add(i);
        }

        it.setContainer(list);
        it.printElemtents();
    }

}
