package base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() {
        System.out.println(Thread.currentThread().getName() + " call()方法执行中...");
        return 1;
    }

}
 class CallableTest {
    public static void main(String[] args) {

        Map map =new HashMap<String,Integer>();
        map.put("1",2);

//        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyCallable());
//        Thread thread = new Thread(futureTask);
//        thread.start();
//        try {
//            Thread.sleep(1000);
//            System.out.println("返回结果 " + futureTask.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        System.out.println(Thread.currentThread().getName() + " main()方法执行完成");
    }

}
