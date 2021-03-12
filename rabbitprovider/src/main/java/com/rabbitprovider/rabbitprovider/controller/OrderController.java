package com.rabbitprovider.rabbitprovider.controller;

import com.rabbitprovider.rabbitprovider.util.RabbitmqMessageSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    RabbitmqMessageSend rabbitmqMessageSend;

    @RequestMapping("/order")
    public  Object order(String message,String rouingKey,String name){
        rabbitmqMessageSend.sendMessage();

        return null;
    }

}
