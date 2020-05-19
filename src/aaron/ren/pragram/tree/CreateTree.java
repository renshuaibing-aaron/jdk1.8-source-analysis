package aaron.ren.pragram.tree;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateTree {

    public static void main(String[] args) {
        Node nodeB=new Node("B",null,"1");
        Node nodeC=new Node("C","1","2");
        Node nodeD=new Node("D","2","3");
        Node nodeE=new Node("E","1","2");
        Node nodeF=new Node("F","2","4");
        List<Node> list=new ArrayList<>();
        list.add(nodeB);
        list.add(nodeC);
        list.add(nodeD);
        list.add(nodeE);
        list.add(nodeF);

        List<Node> childTreeNodes = getChildTreeNodes(list, "1");

        System.out.println(childTreeNodes);
        String json = JSON.toJSONString(childTreeNodes, SerializerFeature.DisableCircularReferenceDetect);
        System.out.println(json);
    }


    public static List<Node> getChildTreeNodes(List<Node> list, String parentId) {


        List<Node> returnList = new ArrayList<>();
        List<Node> childList = new ArrayList<>();
        Node rootNode=new Node();
        for (Node treeNode : list) {
            if(treeNode.getCode()==null){
                System.out.println(treeNode);
            }
            if(treeNode.getCode().equals(parentId)){
                System.out.println("========="+treeNode);
                BeanUtils.copyProperties(treeNode,rootNode);
            }
            if (treeNode.getParentcode() == null) {
                continue;
            }

            if (Objects.equals(treeNode.getParentcode(), parentId)) {

                recursionFn(list, treeNode);
                childList.add(treeNode);
            }

        }
        rootNode.setSubNode(childList);
        returnList.add(rootNode);
        return returnList;
    }
    /**
     * 递归列表
     */
    private static void recursionFn(List<Node> list, Node node) {

        List<Node> childList = getChildList(list, node);
        if(node.getName().equals("E")){
            System.out.println("****childList*****"+childList);

        }
        if (childList.isEmpty()) {
            return;
        }
        node.setSubNode(childList);
        for (Node tChild : childList) {
            recursionFn(list, tChild);
        }
    }

    /**
     * 得到子节点列表
     */
    private static List<Node> getChildList(List<Node> list, Node t) {
        List<Node> tList = new ArrayList<>();

        for (Node treeNode : list) {
            if (treeNode.getParentcode()==null) {
                continue;
            }
            if (Objects.equals(treeNode.getParentcode(), t.getCode())) {
                tList.add(treeNode);
            }
        }
        return tList;
    }

}

