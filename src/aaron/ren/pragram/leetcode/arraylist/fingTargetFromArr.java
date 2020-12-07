package aaron.ren.pragram.Leetcode.arraylist;

/**
 * ����һ�� n*n �Ķ�ά���飬�����ÿ�� ��ÿ�е�����������Ҫ�ҳ� ĳ�� ���� target ������������ҳ���λ��
 */
public class fingTargetFromArr {
    public static void main(String args[]){
        int[][] arr = {
                {1,2,8,9},
                {2,4,9,12},
                {4,7,10,13},
                {6,8,11,15}
        };
        System.out.println(findTarget(arr,6));

    }

    //����ÿ�У�ÿ�е��������ʣ���Ŷ�ǿ��ԴӶ�ά�������½ǵ� 6 ���� ���Ͻǵ� 9 Ϊ��ʼ��
    public static boolean findTarget(int[][] arr,int tar){
        int rows = arr.length;			//����� ����
        int cows = arr[0].length;		//���������
        int i = 0;
        int j = cows - 1;
        while( i < rows && j >= 0){
            int temp = arr[i][j];		//�м����
            if(temp == tar){
                System.out.println("target:" + tar +" in cows:" + (i+1) + " " +"rows:" + (j + 1));
                return true;
            }
            else
            if(temp > tar)
                j--;
            else
                i++;
        }
        System.out.println("Not Found!");
        return false;
    }

}
