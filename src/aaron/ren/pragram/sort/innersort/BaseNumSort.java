package aaron.ren.pragram.sort.innersort;

import java.util.ArrayList;
import java.util.List;

public class BaseNumSort {
    public void sort(int[] array) {
        //����ȷ�����������;
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int time = 0;
        //�ж�λ��;
        while (max > 0) {
            max /= 10;
            time++;
        }
        //����10������;
        List queue = new ArrayList();
        for (int i = 0; i < 10; i++) {
            ArrayList queue1 = new ArrayList();
            queue.add(queue1);
        }
        //����time�η�����ռ�;
        for (int i = 0; i < time; i++) {
            //��������Ԫ��;
            for (int j = 0; j < array.length; j++) {
                //�õ����ֵĵ�time+1λ��;
                int x = array[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                ArrayList queue2 = (ArrayList) queue.get(x);
                queue2.add(array[j]);
                queue.set(x, queue2);
            }
            int count = 0;//Ԫ�ؼ�����;
            //�ռ�����Ԫ��;
            for (int k = 0; k < 10; k++) {
                while (((ArrayList) queue.get(k)).size() > 0) {
                    ArrayList queue3 = (ArrayList) queue.get(k);
                    array[count] = (int) queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }
        }
    }
}
