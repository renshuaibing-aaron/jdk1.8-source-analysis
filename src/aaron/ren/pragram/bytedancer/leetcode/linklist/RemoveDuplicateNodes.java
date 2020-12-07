package aaron.ren.pragram.bytedancer.leetcode.linklist;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 * 示例1:
 *  输入：[1, 2, 3, 3, 2, 1]
 *  输出：[1, 2, 3]
 * 示例2:
 *  输入：[1, 1, 1, 1, 2]
 *  输出：[1, 2]
 */
public class RemoveDuplicateNodes {
    public static void main(String[] args) {
        String[] split={"岗位序列变更","薪酬类别变更"};
        List<String> strs2 = Arrays.asList(split);
        System.out.println("==11========="+strs2.get(0));
        System.out.println("==11========="+strs2.get(1));



        Iterator<String> iterator = strs2.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();

            if("薪酬类别变更".equals(next)){
                iterator.remove();
            }
        }

        System.out.println("====22======="+strs2);
    }
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int element) {
            this.val = element;
        }
    }
}
