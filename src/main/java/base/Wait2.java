package base;

import java.util.concurrent.TimeUnit;

public class Wait2 {
    private Object ob; //获得锁的对象

    public Wait2(Object ob) {
        this.ob = ob;
    }

    public void name() {
        synchronized (ob) {  //对象ob在此处获得锁
            for (int i = 1; i <= 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    try {
                        System.out.println(Thread.currentThread().getName()+"线程等待中");
                        ob.wait();       //使当前线程进入等待状态
                        System.out.println(Thread.currentThread().getName()+"线程已唤醒");
                        try {
                            System.out.println("睡眠5秒后执行剩余部分");
                            TimeUnit.SECONDS.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
