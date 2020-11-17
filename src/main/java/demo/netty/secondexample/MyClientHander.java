package demo.netty.secondexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

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
        System.out.println("服务器远程地址："+ctx.channel().remoteAddress());
        System.out.println("接收服务端消息 :"+msg);
        ctx.channel().writeAndFlush("from cleint "+ LocalDateTime.now());

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ctx.channel().writeAndFlush("from cleint  start");

    }
}
