package base;

public class Wait1 {
    public static void main(String[] args) {
        Object ob = new Object();
        Wait2 w2 = new Wait2(ob);  //给获得锁的对象赋值
        new Thread(() -> {
            w2.name();
        },"t1").start();  //t1线程开启
        new Thread(() -> {
            w2.name();
        },"t2").start();  //t2线程开启
        synchronized (ob) {
            ob.notifyAll();     //唤醒所有线程
        }
    }

}
