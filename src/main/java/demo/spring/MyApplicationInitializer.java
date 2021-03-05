package demo.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by DJ009828 on 2020/11/6
 */
public class MyApplicationInitializer  {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext =new AnnotationConfigApplicationContext(AppConfig.class);
        annotationConfigApplicationContext.refresh();
        HelloService helloService =(HelloService)annotationConfigApplicationContext.getBean("helloService");
        helloService.sayHello();

    }
}
