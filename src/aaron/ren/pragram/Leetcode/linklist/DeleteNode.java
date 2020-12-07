package aaron.ren.pragram.Leetcode.linklist;

/**
 *如何在o(1)的时间内删除链表的指定结点;
 */
public class DeleteNode {
    static class Node {
        int value;
        Node next;
        public Node(int value) {
            this.value = value;
        }

        // 便于打印不能作用与循环链表
        @Override
        public String toString() {
            if (this.next == null) {
                return String.valueOf(this.value);
            }
            return this.value + "->" + this.next.toString();
        }
    }

    /**
     * o(n)时间 删除节点 按照索引位置删除节点
     *
     *
     * @return 返回新的链表(新链表，头结点引用)
     *
     */
    public static Node removeNode(Node head, int index) {
        if (head == null || index < 0) {
            throw new RuntimeException("Invalid param");
        }

        if (index == 0) {
            return head.next;
        }

        Node preNode = head;
        Node curNode = head.next;

        int count = 1;
        while (curNode != null) {
            if (count == index) {
                preNode.next = curNode.next;
                break;
            }
            count++;
            preNode = preNode.next;
            curNode = curNode.next;
        }

        return head;
    }

    /**
     * o(1) 删除指定元素
     */
    public static Node removeNode(Node head, Node toRemove) {
        if (head == null || toRemove == null) {
            return head;
        }
        // 非尾节点
        if (toRemove.next != null) {
            Node toRemoveNext = toRemove.next;
            toRemove.value = toRemoveNext.value;
            toRemove.next = toRemoveNext.next;
            return head;
            // 尾节点
        }else {
            // 头节点即是尾节点
            if (head == toRemove) {
                return null;
            }
            // 找到尾节点的前一个节点
            Node tmp = head;
            // 或者
            // while (tmp.next != null && tmp.next.next != null)
            while (tmp.next != toRemove) {
                tmp = tmp.next;
            }
            tmp.next = null;
            return head;
        }

    }

    public static void main(String args[]) {

        Node head = createTestLinkedList(5, null);

        // 原链表
        System.out.println("origin link: " + head);

        // 删除第二个元素后
        System.out.println("remove third node: " + removeNode(head, 2));

        // 删除第二个元素后
        System.out.println("remove third node: " + removeNode(head, 2));


        Node toRemove = head.next.next;
        // 删除第三个元素
        System.out.println("remove third node: " + removeNode(head, toRemove));

        Node toRemove1 = head.next;
        // 删除第二个元素
        System.out.println("remove second node: " + removeNode(head, toRemove1));

        Node toRemoveHead = head;
        // 删除第一个元素
        System.out.println("remove first node: " + removeNode(head, toRemoveHead));

    }

    private static Node createTestLinkedList(int n, Node addNode) {
        Node head = new Node(0);
        Node curNode = head;
        for (int i = 1; i < n; i++) {
            curNode.next = new Node(i);
            curNode = curNode.next;
        }
        curNode.next = addNode;
        return head;
    }

}
