package demo.设计模式.代理模式.cglib代理;


public class Target {

    public String execute() {
        String message = "-----------test------------";
        System.out.println(message);
        return message;
    }
}