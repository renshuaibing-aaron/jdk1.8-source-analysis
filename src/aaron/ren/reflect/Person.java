package aaron.ren.reflect;

public class Person {
    //˽������
    private String name = "AME";
    //��������
    public int age = 18;
    //���췽��
    public Person() {
    }
    //˽�з���
    private void eat(){
        System.out.println("private eat()...");
    }
    //���з���
    public void play(){
        System.out.println("public play()...");
    }
}