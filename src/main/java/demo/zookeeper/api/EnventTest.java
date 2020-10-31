package demo.zookeeper.api;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class EnventTest {

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        final CountDownLatch countDownLatch =new CountDownLatch(1);
        final ZooKeeper zooKeeper =
                new ZooKeeper("10.7.90.131:2181,10.7.90.130:2181,10.7.90.199:2181",
                        4000, new Watcher() {

                    public void process(WatchedEvent watchedEvent) {
                        System.out.println("全局默认事件："+watchedEvent.getType());
                        if (Event.KeeperState.SyncConnected== watchedEvent.getState()){
                            countDownLatch.countDown();
                        }
                    }
                });

        countDownLatch.await();
        zooKeeper.create("/zdb","0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //通过exists事件绑定
        Stat  stat = zooKeeper.exists("/zdb", new Watcher() {

            public void process(WatchedEvent watchedEvent) {
             System.out.println("监听事件："+watchedEvent.getType()+"->"+watchedEvent.getPath());
                try {
                    //再次绑定事件，实现持续监听
                    zooKeeper.exists(watchedEvent.getPath(),true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Stat stat1 = zooKeeper.setData("/zdb","2".getBytes(),stat.getVersion());

        Thread.sleep(1000);
        zooKeeper.delete("/zdb",stat1.getVersion());




    }
}

