package demo.netty.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by DJ009828 on 2020/11/15
 */
public class NioTest4 {
    public static void main(String[] args) throws Exception {

        FileInputStream fileInputStream = new FileInputStream("input.txt");
        FileOutputStream fileOutputStream =new FileOutputStream("output.txt");

        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer =ByteBuffer.allocate(512);
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
