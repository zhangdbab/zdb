package demo.dubbo.server.impl;

import demo.dubbo.api.IHelloService;

/**
 * Created by DJ009828 on 2020/10/31 18:35
 */
public class HelloServiceImpl implements IHelloService {
    @Override
    public String helle(String msg) {
        return  "hello, "+msg;
    }
}
