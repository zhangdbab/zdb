package demo.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;


public class CuratorEvent {
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework  =
                CuratorFrameworkFactory.builder().
                        connectString("10.7.90.131:2181,10.7.90.130:2181,10.7.90.199:2181").
                        sessionTimeoutMs(10000).
                        retryPolicy(new ExponentialBackoffRetry(1000,3)).
                        namespace("curator"). // 父目录
                        build();
        curatorFramework.start();
//        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/test006","1".getBytes());
        //监听当前节点变化
        addNodeCacheLisster(curatorFramework,"/test");
        //监听子节点子变化
        addNodeCacheChildLisster(curatorFramework,"/test");
        //监听综合节点的变化
        System.in.read();
        curatorFramework.close();

    }

    public    static  void  addNodeCacheLisster(CuratorFramework curatorFramework ,String path ) throws Exception {
      final   NodeCache nodeCache =new NodeCache(curatorFramework,path,false) ;
        NodeCacheListener nodeCacheListener =new NodeCacheListener() {
            public void nodeChanged() throws Exception {
                System.out.println("监听节点的变化....."+nodeCache.getCurrentData().getPath());

            }
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();

    }


    public    static  void  addNodeCacheChildLisster(CuratorFramework curatorFramework ,String path ) throws Exception {


        PathChildrenCache pathChildrenCache  =new PathChildrenCache(curatorFramework,path,true);
        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println("子节点变化："+pathChildrenCache.getCurrentData());
            }
        };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start();

    }


    public    static  void  addNodeCacheTreeLisster(CuratorFramework curatorFramework ,String path ) throws Exception {


//        TreeCache



    }


}
