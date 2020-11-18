package demo.netty.protobuf;

import com.example.protobuf.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Created by DJ009828 on 2020/11/17
 */
public class MyServerHander extends SimpleChannelInboundHandler<DataInfo.Student> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {
        System.out.println("服务端收到消息：");
        System.out.println("name："+  msg.getName()+"age:"+  msg.getAge()+"address:"+msg.getAddress());

    }
}