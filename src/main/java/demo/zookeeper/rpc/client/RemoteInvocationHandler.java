package demo.zookeeper.rpc.client;

import demo.zookeeper.rpc.client.zk.IServiceDiscovery;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by DJ009828 on 2020/10/30 20:30
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private IServiceDiscovery iServiceDiscovery;
    private  String version ;

    public RemoteInvocationHandler(IServiceDiscovery iServiceDiscovery, String version) {
        this.iServiceDiscovery = iServiceDiscovery;
        this.version = version;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest =new RpcRequest
                (method.getDeclaringClass().getName(),method.getName(),args,version);
        //根据接口名找到对应的服务地址
        String serviceAddress = iServiceDiscovery.discovery(rpcRequest.getClassName());
        TCPTransport tcpTransport  =new TCPTransport(serviceAddress);


        return tcpTransport.send(rpcRequest);
    }
}
