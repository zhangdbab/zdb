package demo.zookeeper.rpc.client;

import demo.zookeeper.rpc.client.zk.IServiceDiscovery;

import java.lang.reflect.Proxy;

/**
 * Created by DJ009828 on 2020/10/30 20:23
 */
public class RpcClientProxy {

    private IServiceDiscovery iServiceDiscovery;

    public RpcClientProxy(IServiceDiscovery iServiceDiscovery) {
        this.iServiceDiscovery = iServiceDiscovery;
    }

    public  <T> T clientProxy(final Class<T> interfaceclass,String version  )
   {
     return  (T) Proxy.newProxyInstance(interfaceclass.getClassLoader(),
             new Class[]{interfaceclass},new RemoteInvocationHandler(iServiceDiscovery,version));

   }


}
