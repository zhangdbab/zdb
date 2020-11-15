package demo.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Created by DJ009828 on 2020/11/15
 *  buffer的 Scattering 与 Gathering
 *  从chanel读数据，Scattering 来着一个chanel的数据读到多个buffer中，按照顺序来
 *     场景：数据分门别类的存放在不同的buffer中
 *  向chanel中写数据， Gathering 写到多个chanel中按照顺序来
 *
 */
public class NioTest11 {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel  serverSocketChannel =ServerSocketChannel.open();
        InetSocketAddress  inetSocketAddress =new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(inetSocketAddress);
         int messagelength= 2+3+4;
        ByteBuffer[] byteBuffers =new ByteBuffer[3];
        byteBuffers[0]=ByteBuffer.allocate(2);
        byteBuffers[1]=ByteBuffer.allocate(3);
        byteBuffers[2]=ByteBuffer.allocate(4);
        SocketChannel socketChannel =serverSocketChannel.accept();

        while (true){
             int bytesRead=0;
             while (bytesRead<messagelength){
                 long r =socketChannel.read(byteBuffers);
                 bytesRead+=r;
                 System.out.println("bytesRead:"+bytesRead);
                 Arrays.asList(byteBuffers).stream().map(buffer->"position:"+buffer.position()+",limit:"+buffer.limit())
                         .forEach(System.out::println);
             }

             Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());

             long  bytewride =0 ;
             while (bytewride<messagelength){
                  long r = socketChannel.write(byteBuffers);
                  bytewride+=r;

             }
             Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());
             System.out.println("bytesRead:"+bytesRead+"bytewride:"+bytewride+"messagelength:"+messagelength);
        }





    }
}
