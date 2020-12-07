package aaron.ren.collection.streamandcollectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingByStream {


    public static void main(String[] args) {

        List<User> userList = new ArrayList<User>();
        userList.add(new User("��ʤ��",23,'1'));
        userList.add(new User("��ʤ��",23,'1'));
        userList.add(new User("��ʤ��",22,'Ů'));
        userList.add(new User("��ʤ��",23,'Ů'));
        userList.add(new User("�ƺ���",23,'Ů'));
        userList.add(new User("�ƺ���",22,'Ů'));
        userList.add(new User("�ƺ���",22,'Ů'));
        userList.add(new User("�ƺ���",23,'Ů'));
        userList.add(new User("������",23,'Ů'));
        userList.add(new User("������",22,'Ů'));
        userList.add(new User("������",22,'Ů'));
        userList.add(new User("������",23,'Ů'));


        //���Ҳ̫�����˰�  ��ôʵ�� ��ô���з���
        Map<String, Map<Integer,Long>> boxBarcodeMap = userList.stream().collect(Collectors.groupingBy(User::getName, Collectors.groupingBy(User::getAge,Collectors.counting())));

        for (Map.Entry<String,Map<Integer,Long>> childMap : boxBarcodeMap.entrySet()) {
            System.out.print("����:" + childMap.getKey());
            for (Map.Entry<Integer,Long> item : childMap.getValue().entrySet()) {
                System.out.print(" \t ����:" + item.getKey() + " \t ����:" + item.getValue());
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
