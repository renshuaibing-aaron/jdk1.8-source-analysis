package aaron.ren.collection.streamandcollectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingByStream {


    public static void main(String[] args) {

        List<User> userList = new ArrayList<User>();
        userList.add(new User("杨胜军",23,'1'));
        userList.add(new User("杨胜军",23,'1'));
        userList.add(new User("杨胜军",22,'女'));
        userList.add(new User("杨胜军",23,'女'));
        userList.add(new User("黄海明",23,'女'));
        userList.add(new User("黄海明",22,'女'));
        userList.add(new User("黄海明",22,'女'));
        userList.add(new User("黄海明",23,'女'));
        userList.add(new User("赵忠有",23,'女'));
        userList.add(new User("赵忠有",22,'女'));
        userList.add(new User("赵忠有",22,'女'));
        userList.add(new User("赵忠有",23,'女'));


        //这个也太复杂了吧  怎么实现 怎么进行分组
        Map<String, Map<Integer,Long>> boxBarcodeMap = userList.stream().collect(Collectors.groupingBy(User::getName, Collectors.groupingBy(User::getAge,Collectors.counting())));

        for (Map.Entry<String,Map<Integer,Long>> childMap : boxBarcodeMap.entrySet()) {
            System.out.print("姓名:" + childMap.getKey());
            for (Map.Entry<Integer,Long> item : childMap.getValue().entrySet()) {
                System.out.print(" \t 年龄:" + item.getKey() + " \t 数量:" + item.getValue());
            }
            System.out.print("\n");
        }
    }

     static class User{


        public User(String name, int age, char sex) {
            super();
            this.name = name;
            this.age = age;
            this.sex = sex;
        }
        private String name;
        private int age;
        private char sex;
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
        public char getSex() {
            return sex;
        }
        public void setSex(char sex) {
            this.sex = sex;
        }
    }

}
