package demo.zookeeper.rpc.server.zk;

import demo.zookeeper.rpc.config.ZkConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Created by DJ009828 on 2020/10/31 14:58
 */
public class RegisterCenterImpl  implements  IRegisterCenter{

    private CuratorFramework curatorFramework ;


    {
        curatorFramework = CuratorFrameworkFactory.builder().connectString(
                ZkConfig.CONNECTIION_SRT).sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry
                        (1000,10)).build();
        curatorFramework.start();

    }

    @Override
    public void register(String svcName, String serviceAddress) {

         //服务注册
        String servicePath = ZkConfig.ZK_REGISTER_PATH+"/"+svcName;

        try {
            //判断/servicePath 是否存在，不存在则创建
            if(curatorFramework.checkExists().forPath(servicePath)==null){
                curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).
                        forPath(servicePath,"0".getBytes());
            }
            String  addressPath =servicePath+"/"+serviceAddress;
            String   serviceNode = curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(addressPath,"0".getBytes());
            System.out.println("服务注册成功："+serviceNode);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
