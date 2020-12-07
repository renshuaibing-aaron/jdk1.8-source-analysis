package aaron.ren.pragram.Leetcode.tree;

import java.util.HashMap;
import java.util.Map;

public class DictionaryTree {
    // �ֵ����Ľڵ�
    private class Node {
        // �Ƿ��ǵ���
        private boolean isWord;
        // ���ʼ���
        private int count;
        // �ִ�
        private String str;
        // �ӽڵ�
        private Map<String, Node> childs;
        // ���ڵ�
        private Node parent;

        public Node() {
            childs = new HashMap<String, Node>();
        }

        public Node(boolean isWord, int count, String str) {
            this();
            this.isWord = isWord;
            this.count = count;
            this.str = str;
        }

        public void addChild(String key, Node node) {
            childs.put(key, node);
            node.parent = this;
        }

        public void removeChild(String key) {
            childs.remove(key);
        }

        public String toString() {
            return "str : " + str + ", isWord : " + isWord + ", count : " + count;
        }
    }

    // �ֵ������ڵ�
    private Node root;

    DictionaryTree() {
        // ��ʼ��root
        root = new Node();
    }

    // ����ִ�
    private void addStr(String word, Node node) {

        // ����
        node.count++;

        String str = node.str;
        if (null != str) {

            // Ѱ�ҹ���ǰ׺
            String commonPrefix = "";
            for (int i = 0; i < word.length(); i++) {
                if (str.length() > i && word.charAt(i) == str.charAt(i)) {
                    commonPrefix += word.charAt(i);
                } else {
                    break;
                }
            }

            // �ҵ�����ǰ׺
            if (commonPrefix.length() > 0) {
                if (commonPrefix.length() == str.length() && commonPrefix.length() == word.length()) {
                    // ��֮ǰ�Ĵ��ظ�
                } else if (commonPrefix.length() == str.length() && commonPrefix.length() < word.length()) {
                    // ʣ��Ĵ�
                    String wordLeft = word.substring(commonPrefix.length());
                    // ʣ��Ĵ�ȥ�ӽڵ��м�����
                    searchChild(wordLeft, node);
                } else if (commonPrefix.length() < str.length()) {
                    // �ڵ��ѱ�
                    Node splitNode = new Node(true, node.count, commonPrefix);
                    // �����ѱ�ڵ�ĸ���ϵ
                    splitNode.parent = node.parent;
                    splitNode.parent.addChild(commonPrefix, splitNode);
                    node.parent.removeChild(node.str);
                    node.count--;
                    // �ڵ��ѱ���ʣ���ִ�
                    String strLeft = str.substring(commonPrefix.length());
                    node.str = strLeft;
                    splitNode.addChild(strLeft, node);
                    // �����ѱ���ʣ���ִ�
                    if (commonPrefix.length() < word.length()) {
                        splitNode.isWord = false;
                        String wordLeft = word.substring(commonPrefix.length());
                        Node leftNode = new Node(true, 1, wordLeft);
                        splitNode.addChild(wordLeft, leftNode);
                    }
                }
            } else {
                // û�й�ͬǰ׺��ֱ����ӽڵ�
                Node newNode = new Node(true, 1, word);
                node.addChild(word, newNode);
            }
        } else {
            // �����
            if (node.childs.size() > 0) {
                searchChild(word, node);
            } else {
                Node newNode = new Node(true, 1, word);
                node.addChild(word, newNode);
            }
        }
    }

    // ���ӽڵ�������ִ�
    public void searchChild(String wordLeft, Node node) {
        boolean isFind = false;
        if (node.childs.size() > 0) {
            // ��������
            for (String childKey : node.childs.keySet()) {
                Node childNode = node.childs.get(childKey);
                // ����ĸ��ͬ�����ڸ��ӽڵ��������ִ�
                if (wordLeft.charAt(0) == childNode.str.charAt(0)) {
                    isFind = true;
                    addStr(wordLeft, childNode);
                    break;
                }
            }
        }
        // û������ĸ��ͬ�ĺ��ӣ������Ϊ�ӽڵ�
        if (!isFind) {
            Node newNode = new Node(true, 1, wordLeft);
            node.addChild(wordLeft, newNode);
        }
    }

    // ��ӵ���
    public void add(String word) {
        addStr(word, root);
    }

    // �ڽڵ��в����ִ�
    private boolean findStr(String word, Node node) {
        boolean isMatch = true;
        String wordLeft = word;
        String str = node.str;
        if (null != str) {
            // �ִ��뵥�ʲ�ƥ��
            if (word.indexOf(str) != 0) {
                isMatch = false;
            } else {
                // ƥ�䣬�����ʣ�൥��
                wordLeft = word.substring(str.length());
            }
        }
        // ���ƥ��
        if (isMatch) {
            // �������ʣ�൥�ʳ���
            if (wordLeft.length() > 0) {
                // �������Ӽ�����
                for (String key : node.childs.keySet()) {
                    Node childNode = node.childs.get(key);
                    boolean isChildFind = findStr(wordLeft, childNode);
                    if (isChildFind) {
                        return true;
                    }
                }
                return false;
            } else {
                // û��ʣ�൥�ʳ��ȣ�˵���Ѿ�ƥ����ϣ�ֱ�ӷ��ؽڵ��Ƿ�Ϊ����
                return node.isWord;
            }
        }
        return false;
    }

    // ���ҵ���
    public boolean find(String word) {
        return findStr(word, root);
    }

    // ͳ���ӽڵ��ִ�������
    private int countChildStr(String prefix, Node node) {
        // ��������
        for (String key : node.childs.keySet()) {
            Node childNode = node.childs.get(key);
            // ƥ���ӽڵ�
            int childCount = countStr(prefix, childNode);
            if (childCount != 0) {
                return childCount;
            }
        }
        return 0;
    }

    // ͳ���ִ�������
    private int countStr(String prefix, Node node) {
        String str = node.str;
        // �Ǹ����
        if (null != str) {
            // ǰ׺���ִ���ƥ��
            if (prefix.indexOf(str) != 0 && str.indexOf(prefix) != 0) {
                return 0;
                // ǰ׺ƥ���ִ�����ǰ׺�϶�
            } else if (str.indexOf(prefix) == 0) {
                // �ҵ�Ŀ��ڵ㣬���ص�����
                return node.count;
                // ǰ׺ƥ���ִ������ִ��϶�
            } else if (prefix.indexOf(str) == 0) {
                // ʣ���ִ�����ƥ���ӽڵ�
                String prefixLeft = prefix.substring(str.length());
                if (prefixLeft.length() > 0) {
                    return countChildStr(prefixLeft, node);
                }
            }
        } else {
            // ����㣬ֱ����������
            return countChildStr(prefix, node);
        }
        return 0;
    }

    // ͳ��ǰ׺������
    public int count(String prefix) {
        // �����������
        if (null == prefix || prefix.trim().isEmpty()) {
            return root.count;
        }
        // �Ӹ��������ƥ��
        return countStr(prefix, root);
    }

    // ��ӡ�ڵ�
    private void printNode(Node node, int layer) {
        // �㼶�ݽ�
        for (int i = 0; i < layer; i++) {
            System.out.print("\t");
        }
        // ��ӡ
        System.out.println(node);
        // �ݹ��ӡ�ӽڵ�
        for (String str : node.childs.keySet()) {
            Node child = node.childs.get(str);
            printNode(child, layer + 1);
        }
    }

    // ��ӡ�ֵ���
    public void print() {
        printNode(root, 0);
    }

    public static void main(String[] args) {

        DictionaryTree dt = new DictionaryTree();

        dt.add("interest");
        dt.add("interesting");
        dt.add("interested");
        dt.add("inside");
        dt.add("insert");
        dt.add("apple");
        dt.add("inter");
        dt.add("interesting");
        dt.print();

        boolean isFind = dt.find("inside");
        System.out.println("find inside : " + isFind);

        int count = dt.count("inter");
        System.out.println("count prefix inter : " + count);

    }


}