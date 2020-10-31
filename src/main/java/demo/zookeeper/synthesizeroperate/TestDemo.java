package demo.zookeeper.synthesizeroperate;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by DJ009828 on 2020/10/31 11:52
 *
 */
public class TestDemo {
    public static void main(String[] args) throws IOException {

        CountDownLatch countDownLatch =new CountDownLatch(10);
        for (int i=0; i<10 ;i++){
            new Thread(()->{
                try {
                    countDownLatch.await();
                    DistributedLock distributedLock =new DistributedLock();
                    distributedLock.lock();
                }  catch (Exception e) {
                    e.printStackTrace();
                }

            },"Thread="+i).start();

            countDownLatch.countDown();
        }
        System.in.read();


    }

}
