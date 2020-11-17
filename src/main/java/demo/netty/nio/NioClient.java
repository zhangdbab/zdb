package demo.netty.nio;

import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by DJ009828 on 2020/11/16
 */
public class NioClient {
    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel =SocketChannel.open();
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("localhost",8989));

        while (true){
            int  number= selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys() ;

            for ( SelectionKey selectionKey : selectionKeys){
                if (selectionKey.isConnectable()){
                    SocketChannel  client = (SocketChannel)selectionKey.channel();
                    if (client.isConnectionPending()){
                        client.finishConnect();

                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        //往byteBuffer中放数据
                        byteBuffer.put("连接成功。。。。".getBytes());
                        byteBuffer.flip();
                        //从byteBuffer取数据放到SocketChannel中
                        client.write(byteBuffer);


                        ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                        executorService.submit(()->{
                            byteBuffer.clear();
                            InputStreamReader inputStreamReader =new InputStreamReader(System.in);
                            BufferedReader bufferedReader =new BufferedReader(inputStreamReader);
                            try {
                                String inputmessage = bufferedReader.readLine();
                                byteBuffer.put(inputmessage.getBytes());
                                byteBuffer.flip();
                                client.write(byteBuffer);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        });



                }

            }




        }


    }
}


}
