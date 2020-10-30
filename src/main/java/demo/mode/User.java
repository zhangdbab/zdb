package demo.mode;

import demo.serializ.SuperClass;

import java.io.Serializable;

public class User extends SuperClass implements Serializable  {

    private static final long serialVersionUID = 2350408817825485753L;
    //静态变量不参与序列化
    public   static int  number=8 ;
    private  String name ;
    private  int age ;
    private  transient   String address ;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
