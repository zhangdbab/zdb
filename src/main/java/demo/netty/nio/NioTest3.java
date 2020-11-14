package demo.netty.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by DJ009828 on 2020/11/14
 */
public class NioTest3 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("NioTest3.txt");
        FileChannel fileChannel =fileOutputStream.getChannel();
        ByteBuffer byteBuffer =ByteBuffer.allocate(512);
        byte[] message="aa ds d".getBytes();
        //往byteBuffer中放数据
        for (int a=0;a<message.length;a++){
            byteBuffer.put(message[a]);
        }
        //反转
        byteBuffer.flip();
//        从byteBuffer读数据
        fileChannel.write(byteBuffer);
        fileChannel.close();

    }
}
