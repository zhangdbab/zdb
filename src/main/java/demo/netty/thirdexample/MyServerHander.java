package demo.netty.thirdexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.UUID;

/**
 * Created by DJ009828 on 2020/11/17
 */
public class MyServerHander extends SimpleChannelInboundHandler<String> {

    //用于保存所有Channel对象
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //channel为当前客户端发给服务端的channel
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {

            if(channel != ch){
                ch.writeAndFlush(channel.remoteAddress() + " 发送的消息:" + msg + "\n");
            } else{
                ch.writeAndFlush("[自己]" + msg + " \n");
            }
        });

    }

    //表示连接建立
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //chanel可以理解成Connection
        Channel channel = ctx.channel();
        //广播消息给所有的客户端
        channelGroup.writeAndFlush("[服务器] - " + channel.remoteAddress() + " 加入\n");
        channelGroup.add(channel);
    }

    //表示连接断掉了
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //广播消息给所有的客户端
        channelGroup.writeAndFlush("[服务器] - " + channel.remoteAddress() + " 离开\n");
        //下面这行代码Netty会自动调用
        //channelGroup.remove(channel);
    }

    //表示连接时活动状态
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //广播消息给所有的客户端
        System.out.println(channel.remoteAddress() + " 上线 \n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //广播消息给所有的客户端
        System.out.println(channel.remoteAddress() + " 下线 \n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();;
        ctx.close();
    }
}
