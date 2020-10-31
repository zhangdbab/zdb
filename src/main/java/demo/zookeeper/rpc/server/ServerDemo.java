package demo.zookeeper.rpc.server;

import demo.zookeeper.rpc.common.IUserService;
import demo.zookeeper.rpc.server.zk.IRegisterCenter;
import demo.zookeeper.rpc.server.zk.RegisterCenterImpl;

import java.io.IOException;

/**
 * Created by DJ009828 on 2020/10/30 21:12
 */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        IUserService iUserService =new UserServiceImpl();
        IUserService iUserService2 =new UserServiceImpl2();


        IRegisterCenter registerCenter = new RegisterCenterImpl();

        RpcServer rpcServer =new RpcServer(registerCenter,"127.0.0.1:8080");
        rpcServer.bind(iUserService,iUserService2);
        rpcServer.publisher();
        System.in.read();

    }

}
