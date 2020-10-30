package demo.rpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created by DJ009828 on 2020/10/30 20:30
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private  String host;
    private  int  port ;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest =new RpcRequest
                (method.getDeclaringClass().getName(),method.getName(),args);
        TCPTransport tcpTransport  =new TCPTransport(this.host,this.port);


        return tcpTransport.send(rpcRequest);
    }
}
