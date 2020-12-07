package aaron.ren.pragram.Leetcode.grap;

import java.util.ArrayList;
import java.util.List;

/**
 *https://blog.csdn.net/u013872857/article/details/87999103
 */
public class BFS {
    public static <V> void bfs(List<TreeNode<V>> children, int depth) {
        List<TreeNode<V>> thisChildren, allChildren = new ArrayList<>();
        for (TreeNode<V> child: children) {
            //打印节点值以及深度
            System.out.println(child.getValue().toString() + ",   " + depth);
            thisChildren = child.getChildList();
            if (thisChildren != null && thisChildren.size() > 0) {
                allChildren.addAll(thisChildren);
            }
        }
        if (allChildren.size() > 0)  {
            bfs(allChildren, depth + 1);
        }
    }
}
