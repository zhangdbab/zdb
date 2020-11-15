package demo.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Created by DJ009828 on 2020/11/15
 * Selector
 */
public class NioTest12 {
    public static void main(String[] args) throws Exception {

         int[] ports = new int[5];
         ports[0]=5000;
         ports[1]=5001;
         ports[2]=5002;
         ports[3]=5003;
         ports[4]=5004;
        //Selector 创建
        Selector selector =Selector.open();



    }

}
