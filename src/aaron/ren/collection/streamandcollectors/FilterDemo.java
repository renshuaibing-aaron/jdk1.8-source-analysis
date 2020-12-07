package aaron.ren.collection.streamandcollectors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterDemo {

    public static void main(String[] args) {
        Collection<Person> collection = new ArrayList();
        collection.add(new Person("����", 22, "��"));
        collection.add(new Person("����", 19, "Ů"));
        collection.add(new Person("����", 34, "��"));
        collection.add(new Person("����", 30, "��"));
        collection.add(new Person("����", 25, "Ů"));

        Stream<Person> personStream = collection.stream().filter(new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return "��".equals(person.getGender());//ֻ��������
            }
        });

        collection = personStream.collect(Collectors.toList());//��Streamת��ΪList
        System.out.println(collection.toString());//�鿴���
    }


    public static class Person {
        private String name;//����
        private Integer age;//����
        private String gender;//�Ա�

        public Person(String name, Integer age, String gender) {
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

        //��дtoString������ۿ����
        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", gender='" + gender + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }
}
