package demo.netty.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by DJ009828 on 2020/11/15
 * 文件锁 FileLock
 */
public class NioTest10 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile =new RandomAccessFile("NioTest10.txt","rw");
        FileChannel fileChannel =randomAccessFile.getChannel();

        FileLock fileLock =fileChannel.lock(3,6,true);
        System.out.println("valid:"+fileLock.isValid());
        System.out.println("lock:"+fileLock.isShared());
        fileLock.release();
        randomAccessFile.close();
        fileChannel.close();


    }
}
