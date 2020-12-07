package aaron.ren.pragram.tree;

/**
 * ������������ʵ��
 */
public class BSTTreee {
    public Node root;
    //���ҽڵ�
  //����˼·���Ӹ���ʼ��������ȸ�С���Ͳ������ӣ�����ȸ��󣬾Ͳ����Һ��ӣ������ȣ��򷵻ظýڵ㡣
    public Node find(int key){
        if(root==null){
            System.out.println("The tree is empty...");
            return null;
        }
        Node current=root;
        while (current.element!=key){
            if(current.element>key){
                current=current.leftChild;
            }else{
                current=current.rightChild;
            }
            if(current==null){
                return null;
            }
        }
        return current;
    }
    //����ڵ�
    //����˼·���Ӹ��ڵ㿪ʼ����������ȸ�С��������������ӣ�����ȸ�������������Һ��ӣ�һֱ�鵽ĳ�ڵ����ӻ�����     //          ����Ϊnull�����ڸýڵ��̲���ڵ㡣
    //ע�⣺�������Ԫ��������һ����Ҷ�ӽڵ�
    public boolean insert(Node node){
        if(root==null){
            root=node;
            return true;
        }
        Node current=root;
        if(find(node.element)!=null){
            System.out.println("����������ظ��ڵ�...");
            return false;
        }
        while (current!=null){
            if(current.element>node.element){
                if(current.leftChild==null){
                    current.leftChild=node;
                    return true;
                }
                current=current.leftChild;
            }else{
                if(current.rightChild==null){
                    current.rightChild=node;
                    return true;
                }
                current=current.rightChild;
            }
        }
        return false;
    }
    //ǰ�����(�����ҵ�)
    public void preOrder_iterator(Node node){
        System.out.println(node.element+" ");
        if(node.leftChild!=null){
            preOrder_iterator(node.leftChild);
        }
        if(node.rightChild!=null){
            preOrder_iterator(node.rightChild);
        }
    }
    //�������(�����)
    public void inOrder_iterator(Node node){
        if(node.leftChild!=null){
            inOrder_iterator(node.leftChild);
        }
        System.out.println(node.element+" ");
        if(node.rightChild!=null){
            inOrder_iterator(node.rightChild);
        }
    }
    //�������(���Ҹ�)
    public void postOrder_iterator(Node node){
        if(node.leftChild!=null){
            postOrder_iterator(node.leftChild);
        }
        if(node.rightChild!=null){
            postOrder_iterator(node.rightChild);
        }
        System.out.println(node.element+" ");
    }
    //��ȡ������С�ڵ�
    public Node getMinNode(Node node){
        if(find(node.element)==null){
            System.out.println("This node does not exist...");
            return null;
        }
        Node current=node.leftChild;
        while (current!=null){
            current=current.leftChild;
        }
        return current;
    }
    //��ȡ�������ڵ�
    public Node getMaxNode(Node node){
        if(find(node.element)==null){
            System.out.println("This node does not exist...");
            return null;
        }
        Node current=node.rightChild;
        while (current!=null){
            current=current.rightChild;
        }
        return current;
    }
    //ɾ���ڵ�
    //����˼·��
    //1.�����ҵ�ɾ���ڵ�͸ýڵ��Ӧ�ĸ��׽ڵ㣬�ֱ���target������targetParent�������棬��������һ��������������ɾ���Ľ�     //  ���Ǹ��׵����ӻ����Һ��ӣ�������Ϊtrue���Һ�����Ϊfalse
    //2.���������нڵ�ɾ����ɾ����Ҫ��Ϊ�����������һ����ɾ������Ҷ�ӽڵ㣬����������ɾ�����Ǹ��׵����ӣ���ֱ�ӽ���     //  �׵�leftChild��Ϊtarget�����ӣ������ɾ�������Һ��ӣ���ֱ�ӽ����׵�rightChild��Ϊtarget�����ӣ���Ϊnull���ڶ�    //  �������ɾ���Ľڵ����һ�����ӽڵ㣬�ýڵ���������ӣ�Ҳ�������Һ��ӣ���Ҫ�ֿ��������ɾ���Ľڵ㱾����������     //  �����Ҵ������ӣ���targetParent������ָ��target�����ӣ���������Һ��ӣ���targetParent������ָ��target     //  ���Һ��ӣ����ɾ���Ľڵ㱾���������������Ҵ������ӣ���targetParent���Һ���ָ��target�����ӣ�����ָ���Һ���     //  �������������ɾ�����Ǵ����������ӵĽڵ㣬���������΢���ӣ����ǵ���˵��
    //3.�����������ӵĽڵ�ɾ������������÷�������һ��getFollwingNode�ķ�����Ŀ����Ϊ�˲���ɾ���ڵ�ĺ����ڵ㣬˼·���£�
    //  ��.���ҵ�ɾ���ڵ���������ڵ㣬����getFollowingNode���������ҵ�ɾ���ڵ���������е�����ߵĽڵ㣬Ҳ������С�ڵ㣬    //  �����ظ���С�ڵ㣬��ʱ�������������Ľṹ�������ؽڵ�ĸ��ڵ�ָ�򷵻ؽڵ��������(������ڵĻ���������һ���ǲ�����    //  ��)��
    //  ��.��ʱ��ɾ���ڵ�ĸ��ڵ�ָ��÷��صĽڵ�(ע���������),Ȼ�󽫷��ؽڵ����ָ��ָ��ɾ���ڵ�����ӣ���ָ��ָ��ɾ     //  ���ڵ���Һ��ӣ�������
    public boolean delete(int key){
        if(root==null){
            System.out.println("The tree is empty...");
            return false;
        }
        Node targetParent=root;
        Node target=root;
        boolean isLeftChild=false;
        //�ҵ���Ӧ��ɾ���ڵ�target���丸�ڵ�targetParent
        while (target.element!=key){
            if(key<target.element){
                targetParent=target;
                target=target.leftChild;
                isLeftChild=true;
            }else{
                targetParent=target;
                target=target.rightChild;
                isLeftChild=false;
            }
            if(target==null){
                System.out.println("The target node does not exist...");
                return false;
            }
        }
        //���ɾ���Ľڵ���Ҷ�ӽڵ�
        if(target.leftChild==null&&target.rightChild==null){
            if(root.element==target.element){
                root=null;
                return true;
            }
            if(isLeftChild){
                targetParent.leftChild=target.leftChild;
            }else{
                targetParent.rightChild=target.leftChild;
            }
        }
        //���ɾ���Ľڵ�ֻ��һ���ӽڵ�
        //����ýڵ���������
        else if(target.leftChild!=null&&target.rightChild==null){
            if(root.element==target.element){
                root=target.leftChild;
                return true;
            }
            if(isLeftChild){
                targetParent.leftChild=target.leftChild;
            }else{
                targetParent.leftChild=target.rightChild;
            }
        }
        //����ýڵ���������
        else if(target.leftChild==null&&target.rightChild!=null){
            if(root.element==target.element){
                root=target.rightChild;
                return true;
            }
            if(isLeftChild){
                targetParent.rightChild=target.leftChild;
            }else{
                targetParent.rightChild=target.rightChild;
            }
        }
        else{
            Node followingNode = this.getFollowingNode(target);
            if(target.element == root.element)
                root = followingNode;
            else if(isLeftChild)
                targetParent.leftChild = followingNode;
            else
                targetParent.rightChild = followingNode;
            followingNode.leftChild = target.leftChild;
            followingNode.rightChild = target.rightChild;
        }

        return true;
    }
    //��ȡɾ���ڵ�����ڵ�
    public Node getFollowingNode(Node node2Del){
        Node nodeParent = node2Del;
        //ֻ�б�ɾ���ڵ��������ӽڵ�ʱ���Ż���ø÷���
        //����ֱ�ӵ���rightChild��û�������
        Node node = node2Del.rightChild;
        while(node.leftChild != null){
            nodeParent = node;
            node = node.leftChild;
        }
        if(node.element != node2Del.rightChild.element)
            nodeParent.leftChild = node.rightChild;
        else
            nodeParent.rightChild = node.rightChild;
        return node;
    }




    //����Node�࣬ӵ��Ԫ��ֵ���ڵ����ƣ����ӽڵ㣬�Һ��ӽڵ㣬4����Ա������
  private static   class Node {
        int element;
        String name;
        Node leftChild;
        Node rightChild;

        public Node(int element, String name) {
            this.element = element;
            this.name = name;
        }
    }
}
