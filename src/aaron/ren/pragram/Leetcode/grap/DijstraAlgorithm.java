package aaron.ren.pragram.Leetcode.grap;

import java.util.Scanner;

public class DijstraAlgorithm{
        //��������ΪInteger.MAX_VALUE����������Integer.MAX_VALUE��ӻ�������³��ָ�Ȩ
        public static int MaxValue = 100000;

        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            System.out.println("�����붥�����ͱ���:");
            //������
            int vertex = input.nextInt();
            //����
            int edge = input.nextInt();

            int[][] matrix = new int[vertex][vertex];
            //��ʼ���ڽӾ���
            for (int i = 0; i < vertex; i++) {
                for (int j = 0; j < vertex; j++) {
                    matrix[i][j] = MaxValue;
                }
            }
            for (int i = 0; i < edge; i++) {
                System.out.println("�������" + (i + 1) + "��������Ȩֵ:");
                int source = input.nextInt();
                int target = input.nextInt();
                int weight = input.nextInt();
                matrix[source][target] = weight;
            }

            //��Դ���·����Դ��
            int source = input.nextInt();
            //����dijstra�㷨�������·��
            dijstra(matrix, source);
        }

        public static void dijstra(int[][] matrix, int source) {
            //���·������
            int[] shortest = new int[matrix.length];
            //�жϸõ�����·���Ƿ����
            int[] visited = new int[matrix.length];
            //�洢���·��
            String[] path = new String[matrix.length];

            //��ʼ�����·��
            for (int i = 0; i < matrix.length; i++) {
                path[i] = new String(source + "->" + i);
            }

            //��ʼ��Դ�ڵ�
            shortest[source] = 0;
            visited[source] = 1;

            for (int i = 1; i < matrix.length; i++) {
                int min = Integer.MAX_VALUE;
                int index = -1;

                for (int j = 0; j < matrix.length; j++) {
                    //�Ѿ�������·���Ľڵ㲻��Ҫ�ټ�����㲢�жϼ���ڵ���Ƿ���ڸ���·��
                    if (visited[j] == 0 && matrix[source][j] < min) {
                        min = matrix[source][j];
                        index = j;
                    }
                }

                //�������·��
                shortest[index] = min;
                visited[index] = 1;

                //���´�index���������ڵ�Ľ϶�·��
                for (int m = 0; m < matrix.length; m++) {
                    if (visited[m] == 0 && matrix[source][index] + matrix[index][m] < matrix[source][m]) {
                        matrix[source][m] = matrix[source][index] + matrix[index][m];
                        path[m] = path[index] + "->" + m;
                    }
                }

            }

            //��ӡ���·��
            for (int i = 0; i < matrix.length; i++) {
                if (i != source) {
                    if (shortest[i] == MaxValue) {
                        System.out.println(source + "��" + i + "���ɴ�");
                    } else {
                        System.out.println(source + "��" + i + "�����·��Ϊ��" + path[i] + "����̾����ǣ�" + shortest[i]);
                    }
                }
            }
        }

}
