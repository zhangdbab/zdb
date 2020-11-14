package demo.netty.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by DJ009828 on 2020/11/14
 */
public class NioTest2 {
    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream =new FileInputStream("NioTest2.txt");
        FileChannel fileChannel=fileInputStream.getChannel();
        //读取到byteBuffer中
        ByteBuffer byteBuffer =ByteBuffer.allocate(512);
        fileChannel.read(byteBuffer);
        //操作反转
        byteBuffer.flip();

        while (byteBuffer.hasRemaining()){
            byte b =byteBuffer.get();
            System.out.println((char)b);
        }
        fileChannel.close();

    }
}
