package aaron.ren.collection.unsafe;


import java.util.*;

public class TreeMapTest {

    public static void main(String[] args) {

        TreeMap<Student,String> tm = new TreeMap<Student,String>(new ComparatorByName());

        tm.put(new Student("lisi",38),"beijing");
        tm.put(new Student("zhaoliu",24),"shanghai");
        tm.put(new Student("xiaoqiang",31),"sehnyang");
        tm.put(new Student("wangcai",28),"dalian");


        Iterator<Map.Entry<Student, String>> it = tm.entrySet().iterator();

        while(it.hasNext()){
            Map.Entry<Student,String> me = it.next();
            Student key = me.getKey();
            String value = me.getValue();

            System.out.println(key.getName()+":"+key.getAge()+"---"+value);
        }
        SortedMap<Student, String> rsb = tm.tailMap(new Student("rsb", 38));
        Student student = rsb.isEmpty() ? rsb.firstKey() : rsb.firstKey();

        System.out.println(tm.get(student));


    }






    public static class ComparatorByName implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {

            int temp = o1.getName().compareTo(o2.getName());
            return temp==0? o1.getAge()-o2.getAge(): temp;
        }

    }

    static class Student{

        private String name;
        private int age;

        public Student(String name, int age) {
            this.name=name;
            this.age=age;
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
    }
}


