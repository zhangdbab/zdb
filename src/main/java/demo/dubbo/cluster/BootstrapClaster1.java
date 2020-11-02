package demo.dubbo.cluster;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by DJ009828 on 2020/10/31 18:56
 */
public class BootstrapClaster1 {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext
                =new ClassPathXmlApplicationContext("dubbo-cluster1.xml") ;
        classPathXmlApplicationContext.start();
        System.in.read();

    }

}
