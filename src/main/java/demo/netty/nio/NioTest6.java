package demo.netty.nio;

import com.sun.beans.editors.ByteEditor;

import java.nio.ByteBuffer;

/**
 * Created by DJ009828 on 2020/11/15
 * 分片buffer slicebuffer 与原有的buffer共享数组
 */
public class NioTest6 {
    public static void main(String[] args) {


        ByteBuffer byteBuffer =ByteBuffer.allocate(10);
        for (int a=0;a<byteBuffer.capacity();a++){
            byteBuffer.put((byte)a);
        }
        byteBuffer.position(2);
        byteBuffer.limit(6);
        ByteBuffer  newByteBuffer =byteBuffer.slice();

        for (int a=0;a<newByteBuffer.capacity();a++){
            byte b= newByteBuffer.get(a);
            b *=2;
            newByteBuffer.put(a,b);
        }


        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());
        while (byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());
        }



    }
}
