package com.rabbitprovider.rabbitprovider.util;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class RabbitmqMessageSend {

    @Autowired
    RabbitTemplate rabbitTemplate;




    public void  sendMessage(){
        CorrelationData correlationData = new CorrelationData("订单ID");
        Map<String,Object> map = new HashMap<>();
        map.put("name","123");
        map.put("password","123456");
        rabbitTemplate.convertAndSend("my_rout_exchange", "info", map,correlationData);

    }

}
