package demo.zookeeper.rpc.server.lb;

import demo.zookeeper.rpc.common.IUserService;
import demo.zookeeper.rpc.server.*;
import demo.zookeeper.rpc.server.zk.IRegisterCenter;
import demo.zookeeper.rpc.server.zk.RegisterCenterImpl;

import java.io.IOException;

/**
 * Created by DJ009828 on 2020/10/30 21:12
 */
public class LBServerDemo1 {
    public static void main(String[] args) throws IOException {
        IUserService iUserService =new UserServiceImpl4();
        IRegisterCenter registerCenter = new RegisterCenterImpl();
        RpcServer rpcServer =new RpcServer(registerCenter,"127.0.0.1:8081");
        rpcServer.bind(iUserService);
        rpcServer.publisher();
        System.in.read();

    }

}
