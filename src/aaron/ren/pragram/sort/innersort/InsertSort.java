package aaron.ren.pragram.sort.innersort;

public class InsertSort {
    public void insertSort(int[] a){
        int length=a.length;//���鳤�ȣ��������ȡ������Ϊ������ٶȡ�
        int insertNum;//Ҫ�������
        for(int i=1;i<length;i++){//����Ĵ���
            insertNum=a[i];//Ҫ�������
            int j=i-1;//�Ѿ�����õ�����Ԫ�ظ���
            while(j>=0&&a[j]>insertNum){//���дӺ�ǰѭ����������insertNum��������ƶ�һ��
                a[j+1]=a[j];//Ԫ���ƶ�һ��
                j--;
            }
            a[j+1]=insertNum;//����Ҫ�����������Ҫ�����λ�á�
        }
    }
}
