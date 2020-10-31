package demo.zookeeper.rpc.client;

import demo.zookeeper.rpc.client.zk.IServiceDiscovery;
import demo.zookeeper.rpc.client.zk.ServiceDiscoveryImpl;
import demo.zookeeper.rpc.common.IUserService;
import demo.zookeeper.rpc.config.ZkConfig;

public class ClientDemo {
    public static void main(String[] args) {
        IServiceDiscovery iServiceDiscovery =new ServiceDiscoveryImpl(ZkConfig.CONNECTIION_SRT);
        RpcClientProxy rpcClientProxy =new RpcClientProxy(iServiceDiscovery);
//        IUserService userService =  rpcClientProxy.
//                clientProxy(IUserService.class,"2.0");
        IUserService userService =  rpcClientProxy.
                 clientProxy(IUserService.class,null);
        System.out.println(userService.sayHello("zdb"));


    }
}

