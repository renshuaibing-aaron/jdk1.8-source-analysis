package aaron.ren.pragram.Leetcode.arraylist;

import java.util.Arrays;

/**
 *�������Ǹ������б��е��������г�������ֵ�2�ַ��������磬����[50��2��1,9]���������Ϊ95021��
 */
public class MaxPailie {
    /**
     * 1.����һ���ж�ÿ�����ĳ��ȣ�ͨ���ں��油0�ķ�ʽʹ���е���λ�����,
     * �Ƚϴ�С����,�õ��������
     */
    public static void firstMeans(){
        Integer[] num=new Integer[]{51,9,370,82,4,796};
        String[] str=new String[num.length];

        int n=5;//�趨��ʹ������ÿ��������󳤶�Ϊ5λ;

        //����С��5������ѭ���ں��油0,ֱ������Ϊ5;
        for(int k=0;k<num.length;k++){
            str[k]=num[k].toString();
            while(str[k].length()<n){
                str[k]+="0";
            }
        }
        //��ӡ��0�󰴴�С������;
        System.out.println(Arrays.toString(str));

        //��ð�������˼ά������0�󣬳�����ͬ������Ԫ�ؽ�������;
        //�����������Ҫͬʱ�������������е����λ��;
        for(int i=0;i<num.length-1;i++){
            for(int j=i+1;j<num.length;j++){
                if(Integer.parseInt(str[i])<Integer.parseInt(str[j])){
                    int temp1=num[i];
                    String temp2=str[i];
                    num[i]=num[j];
                    str[i]=str[j];
                    num[j]=temp1;
                    str[j]=temp2;
                }
            }
        }
        //��ӡ�õ���������������;
        System.out.println("����1��"+Arrays.toString(num));
        String resulte="";
        for(int i=0;i<num.length;i++){
            resulte+=num[i];
        }
        System.out.println("���ս��_����1��"+resulte);
    }

    /**
     * 2.����һ������������ϱȽϴ�С,��Ϸ�ʽ���������ǰ��(ð������˼ά);
     */
    public static void secondMeans(){
        Integer[] num2=new Integer[]{51,9,370,82,4,796};
        for(int i=0;i<num2.length-1;i++){
            for(int j=i+1;j<num2.length;j++){
                //�����е���ת��Ϊ�ַ��������������,��Ͻ��ת��Ϊ����;
                int x=Integer.parseInt(num2[i].toString()+num2[j].toString());
                int y=Integer.parseInt(num2[j].toString()+num2[i].toString());
                //�Ƚ���Ͻ��,�����ǰ������Ͻ���󣬾ͰѸ���������ǰ���λ��;
                if(x<y){
                    int temp=num2[i];
                    num2[i]=num2[j];
                    num2[j]=temp;
                }
            }
        }
        //��ӡ�õ���������������;
        System.out.println("����2��"+Arrays.toString(num2));
        String resulte="";
        for(int i=0;i<num2.length;i++){
            resulte+=num2[i];
        }
        System.out.println("���ս��_����2��"+resulte);
    }
}
