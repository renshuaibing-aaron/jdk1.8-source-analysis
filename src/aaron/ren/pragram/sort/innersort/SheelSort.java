package aaron.ren.pragram.sort.innersort;

public class SheelSort {
    public  void sheelSort(int[] a){
        int d  = a.length;
        while (d!=0) {
            d=d/2;
            for (int x = 0; x < d; x++) {//�ֵ�����
                for (int i = x + d; i < a.length; i += d) {//���е�Ԫ�أ��ӵڶ�������ʼ
                    int j = i - d;//jΪ�����������һλ��λ��
                    int temp = a[i];//Ҫ�����Ԫ��
                    for (; j >= 0 && temp < a[j]; j -= d) {//�Ӻ���ǰ������
                        a[j + d] = a[j];//����ƶ�dλ
                    }
                    a[j + d] = temp;
                }
            }
        }
    }
}
