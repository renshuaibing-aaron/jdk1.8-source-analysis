package aaron.ren.pragram.tree;

/**
 * 二叉搜索树的实现
 */
public class BSTTreee {
    public Node root;
    //查找节点
  //方法思路：从根开始找起，如果比根小，就查找左孩子，如果比根大，就查找右孩子，如果相等，则返回该节点。
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
    //插入节点
    //方法思路：从根节点开始遍历，如果比根小，则继续查找左孩子，如果比根大，则继续查找右孩子，一直查到某节点左孩子或者右     //          孩子为null，则在该节点后继插入节点。
    //注意：被插入的元素在树中一定是叶子节点
    public boolean insert(Node node){
        if(root==null){
            root=node;
            return true;
        }
        Node current=root;
        if(find(node.element)!=null){
            System.out.println("不允许出现重复节点...");
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
    //前序遍历(根左右的)
    public void preOrder_iterator(Node node){
        System.out.println(node.element+" ");
        if(node.leftChild!=null){
            preOrder_iterator(node.leftChild);
        }
        if(node.rightChild!=null){
            preOrder_iterator(node.rightChild);
        }
    }
    //中序遍历(左根右)
    public void inOrder_iterator(Node node){
        if(node.leftChild!=null){
            inOrder_iterator(node.leftChild);
        }
        System.out.println(node.element+" ");
        if(node.rightChild!=null){
            inOrder_iterator(node.rightChild);
        }
    }
    //后序遍历(左右根)
    public void postOrder_iterator(Node node){
        if(node.leftChild!=null){
            postOrder_iterator(node.leftChild);
        }
        if(node.rightChild!=null){
            postOrder_iterator(node.rightChild);
        }
        System.out.println(node.element+" ");
    }
    //获取树中最小节点
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
    //获取树中最大节点
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
    //删除节点
    //方法思路：
    //1.首先找到删除节点和该节点对应的父亲节点，分别用target变量和targetParent变量保存，并且利用一个布尔变量保存删除的节     //  点是父亲的左孩子还是右孩子，左孩子则为true，右孩子则为false
    //2.接下来进行节点删除，删除需要分为三种情况，第一种是删除的是叶子节点，该情况是如果删除的是父亲的左孩子，则直接将父     //  亲的leftChild设为target的左孩子，，如果删除的是右孩子，则直接将父亲的rightChild设为target的左孩子，即为null；第二    //  种情况是删除的节点带有一个孩子节点，该节点可以是左孩子，也可以是右孩子，需要分开处理，如果删除的节点本身是左子树     //  ，并且带有左孩子，则将targetParent的左孩子指向target的左孩子，如果带有右孩子，则将targetParent的左孩子指向target     //  的右孩子；如果删除的节点本身是右子树，并且带有左孩子，则将targetParent的右孩子指向target的左孩子，否则指向右孩子     //  。第三种情况是删除的是带有两个孩子的节点，这种情况稍微复杂，我们单独说明
    //3.带有两个孩子的节点删除情况分析：该方法伴有一个getFollwingNode的方法，目的是为了捕获删除节点的后续节点，思路如下，
    //  ①.先找到删除节点的右子树节点，调用getFollowingNode方法可以找到删除节点的右子树中的最左边的节点，也就是最小节点，    //  并返回该最小节点，此时调整该右子树的结构，将返回节点的父节点指向返回节点的右子树(如果存在的话，左子树一定是不存在    //  的)；
    //  ②.此时将删除节点的父节点指向该返回的节点(注意左右情况),然后将返回节点的左指针指向删除节点的左孩子，右指针指向删     //  除节点的右孩子，结束。
    public boolean delete(int key){
        if(root==null){
            System.out.println("The tree is empty...");
            return false;
        }
        Node targetParent=root;
        Node target=root;
        boolean isLeftChild=false;
        //找到对应的删除节点target和其父节点targetParent
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
        //如果删除的节点是叶子节点
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
        //如果删除的节点只有一个子节点
        //如果该节点是左子树
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
        //如果该节点是右子树
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
    //获取删除节点后续节点
    public Node getFollowingNode(Node node2Del){
        Node nodeParent = node2Del;
        //只有被删除节点有左右子节点时，才会调用该方法
        //这里直接调用rightChild是没有问题的
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




    //定义Node类，拥有元素值，节点名称，左孩子节点，右孩子节点，4个成员变量。
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
