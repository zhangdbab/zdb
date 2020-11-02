package demo.dubbo.main;

import java.io.IOException;

public class Bootstrap2 {
    public static void main(String[] args) throws IOException {
        com.alibaba.dubbo.container.Main.main(new String[]{"spring","log4j"});

        System.in.read();
    }
}
