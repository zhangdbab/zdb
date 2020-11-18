package demo.netty.thirdexample.secondexample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by DJ009828 on 2020/11/17
 * 访问方式：启动服务后  curl -v http://localhost:8989/ -v
 */
public class TestClient1 {
    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup clientgroup= new NioEventLoopGroup();

        try {
            Bootstrap bootstrap =new Bootstrap();
            bootstrap.group(clientgroup).channel(NioSocketChannel.class).handler(new MyClientInitializer());
            Channel channel=  bootstrap.connect("localhost",8899).sync().channel();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            for(;;){
                channel.writeAndFlush(br.readLine() + "\r\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            clientgroup.shutdownGracefully();
        }

    }
}
