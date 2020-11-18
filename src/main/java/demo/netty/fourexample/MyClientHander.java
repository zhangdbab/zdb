package demo.netty.fourexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by DJ009828 on 2020/11/17
 */
public class MyClientHander extends SimpleChannelInboundHandler<String> {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }

    @Override
    //读取客户端发送到数据 再返回客户端数据
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("接收服务端消息 :"+msg);
    }


}
