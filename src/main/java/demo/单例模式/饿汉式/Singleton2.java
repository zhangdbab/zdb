package demo.单例模式.饿汉式;

public class Singleton2 {

    private static Singleton2 instance = new Singleton2();

    private Singleton2() {}

    public static Singleton2 getInstance() {
        return instance;
    }

}