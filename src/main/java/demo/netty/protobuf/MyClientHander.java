package demo.netty.protobuf;

import com.example.protobuf.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by DJ009828 on 2020/11/17
 */
public class MyClientHander extends SimpleChannelInboundHandler<DataInfo.Student> {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();

        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        DataInfo.Student student =  DataInfo.Student.newBuilder().setName("张三").setAge(22).setAddress("北京").build();
        ctx.channel().writeAndFlush(student);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {


    }
}
