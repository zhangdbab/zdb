package com.rabbitconsumer.rabbitconsumer.util;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class RabbitmqUtil {




    @RabbitListener(queues = "testQueue")
    public void get(Message message,String string) throws Exception{
        System.out.println(new String(message.getBody(),"UTF-8"));
        System.out.println("消费者1");
    }


}
