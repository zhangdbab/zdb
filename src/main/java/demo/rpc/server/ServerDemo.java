package demo.rpc.server;

import demo.rpc.common.IUserService;

import java.io.IOException;

/**
 * Created by DJ009828 on 2020/10/30 21:12
 */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        IUserService iUserService =new UserServiceImpl();
        RpcServer rpcServer =new RpcServer();
        rpcServer.publisher(iUserService,8888);
    }

}
