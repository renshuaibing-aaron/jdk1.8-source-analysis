package aaron.ren.workskill;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * �����ж��������������
 * �ܶ�ط��õ�������
 */
public class ConvertTreeUtils {

    private static  List<Node>  nodeList;

    static {
        init();
    }

    public static void main(String[] args) {

        List<Node> nodes = convertTree(nodeList, "root");
        System.out.println(JSON.toJSON(nodes));

        List<Node> nodes1 = buildDeptTree(nodeList, "root");
        System.out.println(JSON.toJSON(nodes1));
    }

    /**
     * �����ķ���ʵ��
     * @param list
     * @param root
     * @return
     */
    public static  List<Node> convertTree(List<Node> list,String root) {
        List<Node> trees = new ArrayList<>();
        for (Node item : list) {
            //��ȡ�����ڵ�
            if (item.getParent().equals(root)) {
                trees.add(item);
            }
            //������ȡ���нڵ��µ��ӽڵ����ݣ�ȥ���ӽڵ��б��е��ظ�����
            for (Node it : list) {
                if (it.getParent().equals(item.getCode())) {
                    if (item.getChildren() == null) {
                        item.setChildren(new ArrayList<Node>());
                    }
                    boolean isPut = true;
                    for (Node childItem : item.getChildren()) {
                        if (it.getCode().equals(childItem.getCode())) {
                            isPut = false;
                        }
                    }
                    if (isPut) {
                        item.getChildren().add(it);
                    }
                }
            }
        }
        return trees;
    }

    /**
     *�ݹ�ķ���ʵ��
     * @param dupDeptList
     * @param deptupcode
     * @return
     */
    private static  List<Node> buildDeptTree(List<Node> dupDeptList,String deptupcode ) {
        List<Node> treeList = new ArrayList<>();
        //��ȡ������parentCode���ӽڵ�
        for(Node item:dupDeptList) {
            if (item.getParent().equals(deptupcode)) {
                treeList.add(item);
                //�ݹ�������ӽڵ���ӽڵ��б�
                item.setChildren(buildDeptTree(dupDeptList,item.getCode()));
            }
        }
        return treeList;
    }

    private static void init() {

        nodeList=new ArrayList<>();
        nodeList.add(new Node("0000","china","root"));
        nodeList.add(new Node("0001","zhejiangsheng","0000"));
        nodeList.add(new Node("0002","hennansheng","0000"));
        nodeList.add(new Node("0003","sandongsheng","0000"));
        nodeList.add(new Node("0011","hangzhoushi","0001"));
        nodeList.add(new Node("0022","zhengzhoushi","0002"));
        nodeList.add(new Node("0033","jinanshi","0003"));
        nodeList.add(new Node("0111","binjiangqu","0011"));
        nodeList.add(new Node("0122","zhognyuanqu","0022"));
        nodeList.add(new Node("0222","jinshuiqu","0022"));
        nodeList.add(new Node("1111","changhejiedao","0111"));
    }

    private static   class  Node{

        private String code;
        private String name;

        private  String parent;
        private List<Node> children;

        public Node(String code, String name, String parent) {
            this.code = code;
            this.name = name;
            this.parent = parent;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public List<Node> getChildren() {
            return children;
        }

        public void setChildren(List<Node> children) {
            this.children = children;
        }
    }
}
