package aaron.ren.pragram.Leetcode.math;


/**
 * ��Ŀ����������һ������[a, b]�����������ڡ����������ĸ�����
 * �������Ķ��壺���ڲ�ͬλ�õ�������λ�����һ����λ�����Ҳ���ǰ��0�����������λ��Ϊ������
 * ���磺153������ʹ������3������1���13��13��������������������ͬ��153�����ҵ�31��53ҲΪ������ֻҪ�ҵ�һ��������������������
 * ��������:����Ϊ��������a��b������[a, b]���� (1 �� a �� b �� 10000)��//ע�����Ҫ����a,��b��
 * �������: ���Ϊһ����������ʾ������������������������
 *
 * ��������: 11 20
 * �������: 6
 *
 * @author �޺���367
 * @version ����ʱ�䣺2017��5��22�� ����10:29:55
 * ����˼·����a��b����жϣ��ǲ��ǡ���������������Ŀ�����Ľ�������У���ͨ������forѭ�����ж�������ϵ���λ�����Ƿ����������
 */
public class AmazingNumber  {

    public void amazingNumber1() {
        int tmp=0;
        for(int i=1;i<=9;i++) {
            tmp=tmp*10+i;
            System.out.println(tmp+"*8+"+i+"="+(tmp*8+i));
        }
    }

    public void amazingNumber2() {
        int tmp=0;
        for(int i=1;i<=9;i++) {
            tmp=tmp*10+i;
            int j = i+1;
            System.out.println(tmp+"*9+"+j+"="+(tmp*9+j));
        }
    }

    public void amazingNumber3() {
        int tmp=0;
        for(int i=9;i>=2;i--) {
            tmp=tmp*10+i;
            int j = i-2;
            System.out.println(tmp+"*9+"+j+"="+(tmp*9+j));
        }

    }
}

