package demo.zookeeper.rpc.server.lb;

import demo.zookeeper.rpc.common.IUserService;
import demo.zookeeper.rpc.server.RpcServer;
import demo.zookeeper.rpc.server.UserServiceImpl;
import demo.zookeeper.rpc.server.UserServiceImpl2;
import demo.zookeeper.rpc.server.UserServiceImpl3;
import demo.zookeeper.rpc.server.zk.IRegisterCenter;
import demo.zookeeper.rpc.server.zk.RegisterCenterImpl;

import java.io.IOException;

/**
 * Created by DJ009828 on 2020/10/30 21:12
 */
public class LBServerDemo {
    public static void main(String[] args) throws IOException {
        IUserService iUserService =new UserServiceImpl3();
        IRegisterCenter registerCenter = new RegisterCenterImpl();
        RpcServer rpcServer =new RpcServer(registerCenter,"127.0.0.1:8080");
        rpcServer.bind(iUserService);
        rpcServer.publisher();
        System.in.read();

    }

}
