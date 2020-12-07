package aaron.ren.pragram.Leetcode.grap;

/**
 * �ж�����ͼ�Ƿ���ڻ���2�ַ�������ȱ�������������
 */

import java.util.Scanner;

public class CheckHascircle {
    //�ڽӾ���
    static int[][] graph = new int[200][200];
    //�������ͱߵĸ���
    static int vNum,eNum;
    //��Ǿ���,0Ϊ��ǰ���δ����,1Ϊ���ʹ�,-1��ʾ��ǰ����ߵĽ�㶼�����ʹ���
    static int[] color = new int[200];
    //�Ƿ���DAG�������޻�ͼ��
    static boolean isDAG = true;

    //ͼ����ȱ�������
    void DFS(int i){
        System.out.println("���ڷ��ʽ��"+i);
        //���i��Ϊ���ʹ���״̬
        color[i] = 1;
        for(int j=1;j<=vNum;j++){
            //�����ǰ�����ָ��Ľ��
            if(graph[i][j] != 0){
                //�����Ѿ������ʹ�
                if(color[j] == 1){
                    isDAG = false;//�л�
                    break;
                }else if(color[j] == -1){
                    //��ǰ����ߵĽ�㶼�����ʹ���ֱ��������һ�����
                    continue;
                }else{
                    DFS(j);//����ݹ����
                }
            }
        }
        //���������������Ľ��󣬰ѱ��ڵ���Ϊ-1
        color[i] = -1;
    }

    //����ͼ,���ڽӾ����ʾ
    void create(){
        Scanner sc = new Scanner(System.in);
        System.out.println("���ڴ���ͼ�������붥�����vNum��");
        vNum = sc.nextInt();
        System.out.println("������߸���eNum��");
        eNum = sc.nextInt();
        //��ʼ���ڽӾ���Ϊ0�����3�����㣬����ֱ���1��2��3��
        for(int i=1;i<=vNum;i++){
            for(int j=1;j<=vNum;j++){
                graph[i][j] = 0;
            }
        }
        //����ߵ����
        System.out.println("������ߵ�ͷ��β:");
        for(int k=1;k<=eNum;k++){
            int i = sc.nextInt();
            int j = sc.nextInt();
            graph[i][j] = 1;
        }
        //��ʼ��color����Ϊ0����ʾһ��ʼ���ж��㶼δ�����ʹ�
        for(int i=1;i<=vNum;i++){
            color[i] = 0;
        }
    }

    public static void main(String[] args) {
        CheckHascircle t = new CheckHascircle();
        t.create();
        //��֤ÿ���ڵ㶼���������ų��еĽ��û�бߵ����
        for(int i=1;i<=vNum;i++){
            //�ý���ߵĽ�㶼�����ʹ��ˣ�������
            if(color[i] == -1){
                continue;
            }
            t.DFS(i);
            if(!isDAG){
                System.out.println("�л���");
                break;
            }
        }
        if(isDAG){
            System.out.println("û��������");
        }
    }
}
