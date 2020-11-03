package demo.dubbo.demo;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Protocol;

@Activate
public class User {

    private Protocol protocol ;

    public User(Protocol protocol) {
        this.protocol = protocol;
    }

    @Activate
    public  void  say(){

    }
}
