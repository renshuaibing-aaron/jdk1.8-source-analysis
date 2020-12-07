package aaron.ren.clone.shallow;

public class Student implements Cloneable {

    //��������
    private Subject subject;

    //������������
    private String name;
    private int age;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     *  ��дclone()����
     * @return
     */
    @Override
    public Object clone() {
        try {
            // ֱ�ӵ��ø����clone()����
            Student student = (Student) super.clone();
            return student;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "[Student: " + this.hashCode() + ",subject:" + subject + ",name:" + name + ",age:" + age + "]";
    }
}