package aaron.ren.pragram.Leetcode.arraylist;

import java.util.Stack;

/**
 * https://blog.csdn.net/leonliu1995/article/details/78648157
 * 0-1矩阵中的最大矩形
 * 寻找直方图中的最大矩形
 */
public class RectangleSolution {
    public int LargestRectangleArea(int[] height){
        if (height.length==0) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int i=1, max = height[0];
        stack.push(0);

        while(i< height.length||(i==height.length&& !stack.isEmpty()) ){
            if(i!=height.length && (stack.isEmpty() ||height[i] >= height[stack.peek()])){
                stack.push(i);
                i++;
            }
            else {
                int top  = height[stack.pop()];
                int currMax = !stack.isEmpty()? top *(i - stack.peek()-1): top *i;
                max = Math.max(currMax, max);
            }
        }

        return max;
    }

    public int MaximalRectangle(int[][] rec){
        int[] h = new int[rec[0].length];
        int m = rec.length;
        int n = rec[0].length;
        int max=0;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (i==0)
                    h[j] =rec[i][j];
                else
                    h[j] = rec[i][j]==0? 0:rec[i-1][j]+1;
            }
            max = Math.max(max, LargestRectangleArea(h));

        }

        return max;

    }


    public static void main(String[] args) {
        int[][] rec = {{0,0,1,1,0,1},{0,0,1,1,0,1},{1,1,0,0,0,1},{1,1,1,0,0,1},{1,1,1,0,0,1}};
        int[] test ={2,2,1,0,0};
        RectangleSolution rSolution = new RectangleSolution();
        int max = rSolution.MaximalRectangle(rec);
        int max2 = rSolution.LargestRectangleArea(test);
        System.out.println(max);
        System.out.println(max2);



    }


}
