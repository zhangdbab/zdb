package demo.zookeeper.rpc.server;

import demo.zookeeper.rpc.ano.RpcAnnotation;
import demo.zookeeper.rpc.common.IUserService;
@RpcAnnotation(value = IUserService.class)
public class UserServiceImpl implements IUserService {
    @Override
    public String sayHello(String msg) {
        return  "hello " +msg+ " , I am  version 1.0 ";
    }
}
