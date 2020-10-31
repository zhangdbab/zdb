package demo.zookeeper.rpc.client.zk;

import demo.zookeeper.rpc.client.loadbalence.LoadBalance;
import demo.zookeeper.rpc.client.loadbalence.RandomLoadBanlence;
import demo.zookeeper.rpc.config.ZkConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DJ009828 on 2020/10/31 16:08
 */
public class ServiceDiscoveryImpl  implements  IServiceDiscovery{
    private CuratorFramework curatorFramework ;
    private  String address;

    List<String> repos =new ArrayList<String>();

    public ServiceDiscoveryImpl(String address) {
        this.address = address;
        curatorFramework = CuratorFrameworkFactory.builder().connectString(address).sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry
                        (1000,10)).build();
        curatorFramework.start();
    }



    @Override
    public String discovery(String serviceName) {
        String  path= ZkConfig.ZK_REGISTER_PATH+"/"+serviceName;
        try {
            repos =  curatorFramework.getChildren().forPath(path);

         } catch (Exception e) {

            e.printStackTrace();
        }
        //动态发现服务节点的变化
        registerWather(path);
        //负载均衡
        LoadBalance loadBalance =new RandomLoadBanlence();
        //返回调用服务地址

        return  loadBalance.selectHost(repos);
    }

    private  void    registerWather(final  String path){
        PathChildrenCache pathChildrenCache =  new PathChildrenCache
                (curatorFramework,path,true);

        PathChildrenCacheListener pathChildrenCacheListener =new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                repos=curatorFramework.getChildren().forPath(path);
            }

        };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        try {
            pathChildrenCache.start();
        } catch (Exception e) {
            throw   new RuntimeException("监听注册服务异常"+e);
        }


    }
}
