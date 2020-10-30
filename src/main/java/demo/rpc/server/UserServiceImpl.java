package demo.rpc.server;

import demo.rpc.common.IUserService;

public class UserServiceImpl implements IUserService {
    @Override
    public String sayHello(String msg) {
        return  "hello ,"+msg;
    }
}
