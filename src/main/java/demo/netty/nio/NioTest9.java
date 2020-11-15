package demo.netty.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by DJ009828 on 2020/11/15
 * 内存映射文件 文件的修改在内存中修改 内存与文件同步由操作系统完成
 */
public class NioTest9 {
    public static void main(String[] args) throws IOException {

        RandomAccessFile randomAccessFile =new RandomAccessFile("NioTest9.txt","rw" );
        FileChannel fileChannel =randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer =fileChannel.map(FileChannel.MapMode.READ_WRITE,0,5);
        mappedByteBuffer.put(0,(byte)'a');


    }
}
