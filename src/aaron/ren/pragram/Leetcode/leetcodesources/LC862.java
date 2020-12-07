package aaron.ren.pragram.Leetcode.leetcodesources;

import java.util.Deque;
import java.util.LinkedList;

/**
 * �����������ڣ�����������ΪK��һ�����У�ʹ�����������̣�����̵ĳ��ȡ�
 *
 * ����˼·��P[x]��ʾ���е�ǰ׺�ͣ����ǵ� x1 ,x2 �� x1<x2 ,��P[x1]>=p[x2]����ôѡ��x2�϶����ı�x1�̣�
 *
 * ����y1<y2,�� ���� y1 ��y2����x�㣬��ôy1�϶���y2�á�
 *
 * ά��һ��˫�˶��У�ÿ�����ǰ �� β�˵�P[x]�Ƚϣ�����ԭ����С�򵯳�ԭ����ֵ��
 *
 * �Ͷ��ױȽϣ��������ֵ����ΪK���򽫶��׵�ֵ���С�
 */
public class LC862 {

    public int shortestSubarray(int[] A, int K) {
        int n = A.length;
        long[] p = new long[n+1];
        for(int i=0;i<n;i++) {
            p[i+1] = (A[i] + p[i]);
        }
        Deque<Integer> qt = new LinkedList<>();
        int res = n +1;
        for(int i=0;i<=n;i++)
        {
            System.out.println(p[i]);
            while(!qt.isEmpty() && p[i]<=p[qt.getLast()]) {
                //                System.out.println("!"+" "+p[qt.getLast()]);
                qt.removeLast();
            }
            while(!qt.isEmpty() && p[i]>=p[qt.getFirst()]+K) {
                //                System.out.println("!"+" "+p[qt.getFirst()]);
                res = Math.min(res, i - qt.removeFirst());
                //                System.out.println(qt.getFirst()+" "+p[qt.getFirst()]);
            }
            qt.addLast(i);
        }
        return (res==n+1)?-1:res;
    }


}
