package aaron.ren.collection.list.sort;

import java.util.Comparator;

public class ComparatorDemo {

    public static void main(String[] args) {

        Student stu[]={new Student("zhangsan",20,90.0f),
                new Student("lisi",22,90.0f),
                new Student("wangwu",20,99.0f),
                new Student("sunliu",22,100.0f)};
        java.util.Arrays.sort(stu,new StudentComparator());
        for(Student s:stu)
        {
            System.out.println(s);
        }
    }

   private static  class StudentComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            if(o1.getScore()>o2.getScore()) {
                return -1;
            } else if(o1.getScore()<o2.getScore()) {
                return 1;
            } else{
                if(o1.getAge()>o2.getAge()) {
                    return 1;
                } else if(o1.getAge()<o2.getAge()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }

    }


   private  static  class Student {
        private String name;
        private int age;
        private float score;

        public Student(String name, int age, float score) {
            this.name = name;
            this.age = age;
            this.score = score;
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
        public float getScore() {
            return score;
        }
        public void setScore(float score) {
            this.score = score;
        }

        @Override
        public String toString()
        {
            return name+"\t\t"+age+"\t\t"+score;
        }

    }
}
