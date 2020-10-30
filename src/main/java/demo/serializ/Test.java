package demo.serializ;

import demo.mode.User;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        User user =new User();
        user.setAge(11);
        user.setName("test");
        user.setAddress("1111");
        user.setExe("男");

        ISerializer serializer =new ISerializerImpl();
        try {
            serializer.Serialize(user);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            User user1=(User) serializer.doSerialize(serializer.Serialize(user),User.class);

           System.out.println(user1.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            //transient修饰的不参与序列化
            user.setAddress("2222");
            serializer.SerializeToFile(user);
            User.number=10;
            System.out.println("静态变量不参与序列化:"+User.number);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            User user2 = serializer.doSerializeFromFile(null,User.class);
            System.out.println(user2.getExe());
            System.out.println(user2);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
