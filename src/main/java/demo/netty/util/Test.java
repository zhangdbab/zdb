package demo.netty.util;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * Created by DJ009828 on 2020/12/2
 */
public class Test {
    public static void main(String[] args) {


    System.out.println(SystemPropertyUtil.getInt(
            "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
    }
}

