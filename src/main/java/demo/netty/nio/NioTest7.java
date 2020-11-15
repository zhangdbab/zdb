package demo.netty.nio;

import com.sun.beans.editors.ByteEditor;

import java.nio.ByteBuffer;

/**
 * Created by DJ009828 on 2020/11/15
 * 只读buffer HeapByteBufferR
 * 只读buffer不能转化为读写buffer
 */
public class NioTest7 {
    public static void main(String[] args) {

        ByteBuffer byteBuffer =ByteBuffer.allocate(10);
        System.out.println(byteBuffer.getClass());//class java.nio.HeapByteBuffer
        for (int a =0 ;a<byteBuffer.capacity();a++){

            byteBuffer.put((byte)a);
        }

        ByteBuffer readonlyBuffer = byteBuffer.asReadOnlyBuffer();

        System.out.println(readonlyBuffer.getClass());//class java.nio.HeapByteBufferR

        readonlyBuffer.position(0);
        //具体实现直接在put方法中抛异常
        readonlyBuffer.put((byte)2);//Exception in thread "main" java.nio.ReadOnlyBufferException



    }
}
