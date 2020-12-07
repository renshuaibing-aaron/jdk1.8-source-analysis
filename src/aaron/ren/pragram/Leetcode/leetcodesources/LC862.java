package aaron.ren.pragram.Leetcode.leetcodesources;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 求满足数组内，连续和至少为K的一个序列，使得这个序列最短，求最短的长度。
 *
 * 解题思路：P[x]表示数列的前缀和，考虑点 x1 ,x2 若 x1<x2 ,且P[x1]>=p[x2]，那么选择x2肯定来的比x1短；
 *
 * 考虑y1<y2,若 满足 y1 ，y2都是x点，那么y1肯定比y2好。
 *
 * 维护一个双端队列，每次入队前 和 尾端的P[x]比较，若比原来的小则弹出原来的值；
 *
 * 和队首比较，若满足差值至少为K，则将队首的值出列。
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
