package demo.设计模式.单例模式.懒汉式;

/**
 * 双检锁/双重校验锁 + volatile关键字
 */
public class Singleton1 {

    private static volatile Singleton1 instance = null;

    private Singleton1() {}

    public static Singleton1 getInstance() {
        if (instance == null) {
            synchronized (Singleton1.class) {
                if (instance == null) {
                    instance = new Singleton1();
                }
            }
        }

        return instance;
    }

}