package aaron.ren.clone.deep;

public class Subject implements Cloneable {

    private String name;

    public Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //Subject ���Ҳ���������͵ĳ�Ա���ԣ�ҲӦ�ú� Student ��һ��ʵ��
        return super.clone();
    }

    @Override
    public String toString() {
        return "[Subject: " + this.hashCode() + ",name:" + name + "]";
    }
}