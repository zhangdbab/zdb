package demo.netty.firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by DJ009828 on 2020/11/17
 * 访问方式：启动服务后  curl -v http://localhost:8989/ -v
 */
public class TestServer {
    public static void main(String[] args) {
        //接受客户端连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //连接处理
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            //ServerBootstrap 辅助封装类 助手类
            ServerBootstrap serverBootstrap =new ServerBootstrap();
            //
            serverBootstrap.group(bossGroup,workGroup).
                    channel(NioServerSocketChannel.class).
                    childHandler(new TestServerInitializer());//childHandler对请求处理
            //服务启动
            ChannelFuture channelFuture =serverBootstrap.bind(8989).sync();
            //关闭操作
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //优雅关闭
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }
}
