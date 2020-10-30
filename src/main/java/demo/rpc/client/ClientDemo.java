package demo.rpc.client;

import demo.rpc.common.IUserService;

public class ClientDemo {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy =new RpcClientProxy();
        IUserService userService =  rpcClientProxy.clientProxy(IUserService.class,"localhost",8888);
        System.out.println(userService.sayHello("zdb"));


    }
}

