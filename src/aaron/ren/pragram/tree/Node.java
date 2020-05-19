package aaron.ren.pragram.tree;

import java.util.List;

class Node {
    String name ;
    String parentcode;

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", parentcode='" + parentcode + '\'' +
                ", code='" + code + '\'' +
                ", subNode=" + subNode +
                '}';
    }

    String code;

    public Node(String name, String parentcode, String code) {
        this.name = name;
        this.parentcode = parentcode;
        this.code = code;
    }

    public Node() {
    }

    public List<Node> getSubNode() {
        return subNode;
    }

    public void setSubNode(List<Node> subNode) {
        this.subNode = subNode;
    }

    List<Node> subNode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentcode() {
        return parentcode;
    }

    public void setParentcode(String parentcode) {
        this.parentcode = parentcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}