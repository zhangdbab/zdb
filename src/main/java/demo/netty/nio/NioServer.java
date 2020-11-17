package demo.netty.nio;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by DJ009828 on 2020/11/16
 */
public class NioServer {
    private  static Map<String ,SocketChannel> socketChannelmap =new HashMap<>();

    public static void main(String[] args) throws IOException {



        ServerSocketChannel serverSocketChannel =ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8989));

        Selector selector =Selector.open();
        //注册
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            //一直阻塞在这里
            selector.select();
            Set<SelectionKey> selectionKeys =  selector.selectedKeys();
            selectionKeys.forEach(
            selectionKey -> {
                final SocketChannel client;
                if (selectionKey.isAcceptable()){
                    //获取当前事件发生在哪个通道上面 ，前面注册的为 ServerSocketChannel ，故取ServerSocketChannel
                    ServerSocketChannel serverSocketChannel1 =(ServerSocketChannel)selectionKey.channel();
                    try {
                        client=serverSocketChannel1.accept();
                        client.configureBlocking(false);
                        client.register(selector,SelectionKey.OP_READ);
                        String key = "["+UUID.randomUUID()+"]";
                        socketChannelmap.put(key,client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else  if (selectionKey.isReadable()){

                    ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                    client =(SocketChannel)selectionKey.channel();
                    try {
                      int number =    client.read(byteBuffer);
                      if (number>0){
                          byteBuffer.flip();
                          Charset charset =Charset.forName("utf-8");
                          String recivmessage = String.valueOf(charset.decode(byteBuffer).array());
                          System.out.println(client+"revmessage:"+recivmessage);
                          String sendkye=null;
                          for (Map.Entry<String ,SocketChannel> entry :socketChannelmap.entrySet()){
                              if(entry.getValue()==client){
                                  sendkye=entry.getKey();
                                  break;
                              }
                          }

                          for (Map.Entry<String ,SocketChannel> entry :socketChannelmap.entrySet()){
                              ByteBuffer    writeBuffer = ByteBuffer.allocate(1024);
                              String responsMesssage = sendkye+"->"+recivmessage;
                              writeBuffer.put(responsMesssage.getBytes());
                              writeBuffer.flip();
                              entry.getValue().write(writeBuffer);

                          }


                      }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                selectionKeys.clear();
            }
            );



        }





    }
}
