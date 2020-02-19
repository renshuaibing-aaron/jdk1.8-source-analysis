package aaron.ren.reflect;

public class Person {
    //私有属性
    private String name = "AME";
    //公有属性
    public int age = 18;
    //构造方法
    public Person() {
    }
    //私有方法
    private void eat(){
        System.out.println("private eat()...");
    }
    //公有方法
    public void play(){
        System.out.println("public play()...");
    }
}