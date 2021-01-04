package demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建固定核心线程大小的线程池
 */
public class Demo001 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int a =0 ;a<10;a++){
            executorService.execute(()->System.out.println("hello........"));
        }
    }
}
