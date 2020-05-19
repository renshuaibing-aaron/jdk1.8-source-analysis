package aaron.ren.pragram.tree;

public class BeanUtils {
    public static void copyProperties(Node treeNode, Node rootNode) {
        rootNode.setName(treeNode.getName());
        rootNode.setCode(treeNode.getCode());
        rootNode.setParentcode(treeNode.getParentcode());
    }
}
