package aaron.ren.pragram.sort.innersort;

public class SelectSort {
    public void selectSort(int[] a) {
        int length = a.length;
        for (int i = 0; i < length; i++) {//ѭ������
            int key = a[i];
            int position=i;
            for (int j = i + 1; j < length; j++) {//ѡ����С��ֵ��λ��
                if (a[j] < key) {
                    key = a[j];
                    position = j;
                }
            }
            a[position]=a[i];//����λ��
            a[i]=key;
        }
    }
}
