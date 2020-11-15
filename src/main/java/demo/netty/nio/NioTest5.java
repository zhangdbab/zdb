package demo.netty.nio;

import java.nio.ByteBuffer;

/**
 * Created by DJ009828 on 2020/11/15
 * 类型化的put与get
 */
public class NioTest5 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer =ByteBuffer.allocate(64);
        byteBuffer.putInt(15);
        byteBuffer.putLong(10000L);
        byteBuffer.flip();
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        }
}
