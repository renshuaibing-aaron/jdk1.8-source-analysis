package aaron.ren.object.serial;

import java.io.*;

/**
 * java  ���л������о�
 */
public class SerialDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //���л�
        FileOutputStream fos = new FileOutputStream("object.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        User user1 = new User("xuliugen", "123456", "male");
        oos.writeObject(user1);
        oos.flush();
        oos.close();
        //�����л�
        FileInputStream fis = new FileInputStream("object.out");
        ObjectInputStream ois = new ObjectInputStream(fis);
        User user2 = (User) ois.readObject();
        System.out.println(user2.getUserName()+ " " +
                user2.getPassword() + " " + user2.getSex());
        //�����л���������Ϊ��xuliugen 123456 male
    }

    public static class User implements Serializable {
        public User(String userName, String password, String sex) {
            this.userName = userName;
            this.password = password;
            this.sex = sex;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        private String userName;
        private String password;
        private String sex;
        //ȫ�ι��췽����get��set����ʡ��
    }
}
