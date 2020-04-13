package aaron.ren.pragram.sort.innersort;

import java.util.ArrayList;
import java.util.List;

public class BaseNumSort {
    public void sort(int[] array) {
        //首先确定排序的趟数;
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int time = 0;
        //判断位数;
        while (max > 0) {
            max /= 10;
            time++;
        }
        //建立10个队列;
        List queue = new ArrayList();
        for (int i = 0; i < 10; i++) {
            ArrayList queue1 = new ArrayList();
            queue.add(queue1);
        }
        //进行time次分配和收集;
        for (int i = 0; i < time; i++) {
            //分配数组元素;
            for (int j = 0; j < array.length; j++) {
                //得到数字的第time+1位数;
                int x = array[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                ArrayList queue2 = (ArrayList) queue.get(x);
                queue2.add(array[j]);
                queue.set(x, queue2);
            }
            int count = 0;//元素计数器;
            //收集队列元素;
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
