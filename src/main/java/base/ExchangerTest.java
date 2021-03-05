package base;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangerTest {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
         Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                String method1 = "method1";
               System.out.println("线程1到达同步点...");
                try {
                    String strFromThread2 = exchanger.exchange(method1);
                   System.out.println("线程1获取的交换数据是{}"+ strFromThread2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               System.out.println("线程1运行结束");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                String method2 = "method2";
                try {
                   System.out.println("线程2开始执行...");
                    // 让线程1先达到同步点
                    TimeUnit.SECONDS.sleep(3);
                   System.out.println("线程2达到同步点...");
                    String strFromThread1 = exchanger.exchange(method2);
                   System.out.println("线程2获取的交换数据是{}" + strFromThread1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               System.out.println("线程2运行结束");
            }
        });

        thread1.start();
        thread2.start();
    }
}
