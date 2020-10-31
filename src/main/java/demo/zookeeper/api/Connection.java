package demo.zookeeper.api;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import javax.sound.midi.Synthesizer;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class Connection     {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        final CountDownLatch countDownLatch =new CountDownLatch(1);
        ZooKeeper zooKeeper =
                new ZooKeeper("10.7.90.131:2181,10.7.90.130:2181,10.7.90.199:2181",
                        4000, new Watcher() {
                    public void process(WatchedEvent watchedEvent) {
                        if (Event.KeeperState.SyncConnected== watchedEvent.getState()){
                            countDownLatch.countDown();
                        }
                    }
                });

        countDownLatch.await();
        System.out.println(zooKeeper.getState());//CONNECTING
        Stat stat = new Stat();
        zooKeeper.create("/zdb","0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        byte[]   bytes=  zooKeeper.getData("/zdb",null , stat);
        //stat 节点信息
        System.out.println("更新。。。。。");
        byte[]   bytes1=  zooKeeper.getData("/zdb",null , stat);
        zooKeeper.setData("/zdb","1233".getBytes(),stat.getVersion()) ;
        System.out.println("删除。。。。。");
        byte[]   bytes2=  zooKeeper.getData("/zdb",null , stat);
        zooKeeper.delete("/zdb",stat.getVersion());
        zooKeeper.close();
        System.out.println("事件机制：----------------------------");

    }
}
