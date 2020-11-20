package demo.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by DJ009828 on 2020/11/6
 */
@Configuration
//@ComponentScan("demo.spring")
public class AppConfig {
    @Bean

    public  HelloService helloService(){
        return new HelloService();
    }
}
