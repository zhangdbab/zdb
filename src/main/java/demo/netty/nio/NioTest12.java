package demo.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by DJ009828 on 2020/11/15
 * Selector
 */
public class NioTest12 {
    public static void main(String[] args) throws Exception {

        int[] ports = new int[5];
        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;
        Selector selector =Selector.open();

        for (int a=0;a<ports.length;a++){
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket =serverSocketChannel.socket();
            InetSocketAddress inetSocketAddress =new InetSocketAddress(ports[a]);
            serverSocket.bind(inetSocketAddress);
            //绑定  客户端发起连接的时候
            serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
            System.out.println("监听端口成功,端口号："+ports[a]);
        }


        while (true){

            selector.select();
            Set<SelectionKey>  selectionKeys= selector.selectedKeys();

            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();

            while (selectionKeyIterator.hasNext()){
                SelectionKey selectionKey = selectionKeyIterator.next();

                if(selectionKey.isAcceptable()){
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
                    SocketChannel socketChannel= serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    selectionKeyIterator.remove();
                    System.out.println("获得客户端连接："+serverSocketChannel);
                }else if(selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    int byteRead=0;
                    while (true){
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        byteBuffer.clear();
                        //从socketChannel里面读数据
                        int read = socketChannel.read(byteBuffer);
                        if (read==0){
                            break;
                        }
                        byteBuffer.flip();
                        //往socketChannel里面写数据
                        socketChannel.write(byteBuffer);

                        byteRead+=read;
                    }
                    System.out.println("byteRead:"+byteRead);
                    selectionKeyIterator.remove();


                }

            }


        }



    }

}
