package base;

import java.util.Date;

public class MyRunnable2 implements Runnable {

    private String command;

    public MyRunnable2(String s) {
        this.command = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date());
    }

    private void processCommand() {
//      System.out.println("");
    }

    @Override
    public String toString() {
        return this.command;
    }
}