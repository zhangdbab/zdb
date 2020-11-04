package demo.dubbo.adpative;

import com.alibaba.dubbo.common.extension.ExtensionFactory;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Protocol;

import java.io.IOException;

/**
 * Created by DJ009828 on 2020/10/31 18:56
 */
public class Test {
    public static void main(String[] args) throws IOException {


//        Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("myProtocol");
//        System.out.println(protocol.getDefaultPort());

        //动态创建一个适配器
        Protocol protocol1 = ExtensionLoader.
                getExtensionLoader(Protocol.class).
                getAdaptiveExtension();

        System.out.println(protocol1.getDefaultPort());

        System.out.println("11");


//        Protocol protocol3 = ExtensionLoader.getExtensionLoader(Protocol.class).getDefaultExtension();
//        System.out.println(    protocol3.getDefaultPort());


    }

}
