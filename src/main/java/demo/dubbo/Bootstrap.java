package demo.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by DJ009828 on 2020/10/31 18:56
 */
public class Bootstrap {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext
                =new ClassPathXmlApplicationContext("dubbo-server.xml") ;
        classPathXmlApplicationContext.start();
        System.in.read();

    }

}
