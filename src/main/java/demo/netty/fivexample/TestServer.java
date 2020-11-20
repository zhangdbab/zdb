package demo.netty.fivexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by DJ009828 on 2020/11/17
 * 访问方式：启动服务后  curl -v http://localhost:8989/ -v
 */
public class TestServer {
    public static void main(String[] args) throws InterruptedException {
       EventLoopGroup bossgroup = new NioEventLoopGroup();
       EventLoopGroup workgroup = new NioEventLoopGroup();
       try {
           ServerBootstrap serverBootstrap =new ServerBootstrap();
           serverBootstrap.group(bossgroup,workgroup).
                   channel(NioServerSocketChannel.class).
                   handler(new LoggingHandler(LogLevel.INFO)).
                   childHandler(new MyServerInitializer());
           ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
           channelFuture.channel().closeFuture().sync();
       }finally {
           bossgroup.shutdownGracefully();
           workgroup.shutdownGracefully();
       }


    }
}
