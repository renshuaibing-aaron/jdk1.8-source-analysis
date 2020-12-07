package aaron.ren.collection.list.sort;

/**
 * 进行排序的方法 这个是利用Comparable 方法
 */
public class ComparableDemo {

    public static void main(String[] args) {

        Student stu[] = {new Student("zhangsan", 20, 90.0f),
                new Student("lisi", 22, 90.0f),
                new Student("wangwu", 20, 99.0f),
                new Student("sunliu", 22, 100.0f)};
        java.util.Arrays.sort(stu);
        for (Student s : stu) {
            System.out.println(s);
        }
    }

    private static class Student implements Comparable<Student> {
        private String name;
        private int age;
        private float score;

        public Student(String name, int age, float score) {
            this.name = name;
            this.age = age;
            this.score = score;
        }

        @Override
        public String toString() {
            return name + "\t\t" + age + "\t\t" + score;
        }

        @Override
        public int compareTo(Student o) {

            if (this.score > o.score)//score是private的，为什么能够直接调用,这是因为在Student类内部
            {
                return -1;//由高到底排序
            } else if (this.score < o.score) {
                return 1;
            } else {
                return Integer.compare(this.age, o.age);//由底到高排序
            }
        }
    }

}
