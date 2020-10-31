package demo.zookeeper.api;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class CuratorTest {
    public static void main(String[] args) throws Exception {
        CuratorFramework curator =
                CuratorFrameworkFactory.builder().
                        connectString("10.7.90.131:2181,10.7.90.130:2181,10.7.90.199:2181").
                        sessionTimeoutMs(10000).
                        retryPolicy(new ExponentialBackoffRetry(1000,3)).
                        namespace("curator"). // 父目录
                        build();
        curator.start();
        curator.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/test004","1".getBytes());
//        curator.delete().deletingChildrenIfNeeded().forPath("/test001");
        Stat stat =new Stat();
        curator.getData().storingStatIn(stat).forPath("/test004");
        curator.setData().withVersion(stat.getVersion()).forPath("/test004","4".getBytes());
        curator.close();


    }
}
