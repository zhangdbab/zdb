package demo.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by DJ009828 on 2020/11/15
 * DirectByteBuffer
 * DirectByteBuffer  内存泄漏不会发生   address 变量对应堆外内存的引用
 *
 *
 */
public class NioTest8 {
    public static void main(String[] args) throws Exception {

        FileInputStream fileInputStream = new FileInputStream("input2.txt");
        FileOutputStream fileOutputStream =new FileOutputStream("output2.txt");

        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();
//        ByteBuffer byteBuffer =ByteBuffer.allocate(512);

        ByteBuffer byteBuffer =ByteBuffer.allocateDirect(512);

        while (true){
            byteBuffer.clear();
            int read =inputChannel.read(byteBuffer);
            System.out.println("read:"+read);
            if(-1==read){
                break;
            }
            byteBuffer.flip();
            outputChannel.write(byteBuffer);
        }
        inputChannel.close();
        outputChannel.close();

    }
}
