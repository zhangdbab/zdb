package demo.zookeeper.rpc.server;

import demo.zookeeper.rpc.ano.RpcAnnotation;
import demo.zookeeper.rpc.common.IUserService;

@RpcAnnotation(value = IUserService.class )
public class UserServiceImpl4 implements IUserService {
    @Override
    public String sayHello(String msg) {

        return  "hello " +msg+ " , I am  8081 ";
    }
}
