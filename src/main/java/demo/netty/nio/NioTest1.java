package demo.netty.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * Created by DJ009828 on 2020/11/14
 */
public class NioTest1 {
    public static void main(String[] args) {


        //创建大小为10的缓存区
        IntBuffer buffer = IntBuffer.allocate(10);
        for(int i=0;i<buffer.capacity();i++){
            int randaomNumber = new SecureRandom().nextInt(20);
            //数据存放
            buffer.put(randaomNumber);

        }
        //状态的反转 写转为读状态
        buffer.flip();
        //取出来
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }

    }
}
