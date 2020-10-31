package demo.zookeeper.rpc.server;

import demo.zookeeper.rpc.ano.RpcAnnotation;
import demo.zookeeper.rpc.common.IUserService;

@RpcAnnotation(value = IUserService.class,version ="2.0" )
public class UserServiceImpl2 implements IUserService {
    @Override
    public String sayHello(String msg) {

        return  "hello " +msg+ " , I am  version 2.0 ";
    }
}
