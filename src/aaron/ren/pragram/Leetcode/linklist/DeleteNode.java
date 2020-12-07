package aaron.ren.pragram.Leetcode.linklist;

/**
 *�����o(1)��ʱ����ɾ�������ָ�����;
 */
public class DeleteNode {
    static class Node {
        int value;
        Node next;
        public Node(int value) {
            this.value = value;
        }

        // ���ڴ�ӡ����������ѭ������
        @Override
        public String toString() {
            if (this.next == null) {
                return String.valueOf(this.value);
            }
            return this.value + "->" + this.next.toString();
        }
    }

    /**
     * o(n)ʱ�� ɾ���ڵ� ��������λ��ɾ���ڵ�
     *
     *
     * @return �����µ�����(������ͷ�������)
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
     * o(1) ɾ��ָ��Ԫ��
     */
    public static Node removeNode(Node head, Node toRemove) {
        if (head == null || toRemove == null) {
            return head;
        }
        // ��β�ڵ�
        if (toRemove.next != null) {
            Node toRemoveNext = toRemove.next;
            toRemove.value = toRemoveNext.value;
            toRemove.next = toRemoveNext.next;
            return head;
            // β�ڵ�
        }else {
            // ͷ�ڵ㼴��β�ڵ�
            if (head == toRemove) {
                return null;
            }
            // �ҵ�β�ڵ��ǰһ���ڵ�
            Node tmp = head;
            // ����
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

        // ԭ����
        System.out.println("origin link: " + head);

        // ɾ���ڶ���Ԫ�غ�
        System.out.println("remove third node: " + removeNode(head, 2));

        // ɾ���ڶ���Ԫ�غ�
        System.out.println("remove third node: " + removeNode(head, 2));


        Node toRemove = head.next.next;
        // ɾ��������Ԫ��
        System.out.println("remove third node: " + removeNode(head, toRemove));

        Node toRemove1 = head.next;
        // ɾ���ڶ���Ԫ��
        System.out.println("remove second node: " + removeNode(head, toRemove1));

        Node toRemoveHead = head;
        // ɾ����һ��Ԫ��
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
