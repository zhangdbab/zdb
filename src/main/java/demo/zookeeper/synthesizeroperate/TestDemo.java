package demo.zookeeper.synthesizeroperate;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
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

    public  static    void CuratorSynthensizer() throws Exception {
        CuratorFramework curator =
                CuratorFrameworkFactory.builder().
                        connectString("10.7.90.131:2181,10.7.90.130:2181,10.7.90.199:2181").
                        sessionTimeoutMs(10000).
                        retryPolicy(new ExponentialBackoffRetry(1000,3)).
                        namespace("curator"). // 父目录
                        build();
        curator.start();
        InterProcessMutex interProcessMutex =
                new InterProcessMutex(curator,"/locaks");

        interProcessMutex.acquire();
        // to do 基于 CuratorFramework  演示分布式锁




    }

}
