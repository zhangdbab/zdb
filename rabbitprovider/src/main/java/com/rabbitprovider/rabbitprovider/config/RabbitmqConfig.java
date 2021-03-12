package com.rabbitprovider.rabbitprovider.config;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
@ComponentScan("com")
public class RabbitmqConfig {


    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost",5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("test");
        //是否开启消息确认机制
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }



    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(){
        TomcatServletWebServerFactory tomcatServletWebServerFactory
                =new TomcatServletWebServerFactory(8081);

        return tomcatServletWebServerFactory;
    }
    //发送确认，
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
         rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//             消息达到交换机后调用
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("=====发送确认=====");
//                ack 有没有发送成功
                System.out.println("是否发送成功:"+ack);
//                cause 失败的原因
                System.out.println("失败的原因:"+cause);
//                 业务id传入CorrelationData中
                System.out.println("发送消息信息:"+correlationData);
            }
        });
        //开启失败回调
        rabbitTemplate.setMandatory(true);
        //失败回调消息为进入消息队列时调用
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("======失败回调======");
                System.out.println("发送信息："+message);
                System.out.println("响应码："+replyCode);
                System.out.println("响应内容："+replyText);
                System.out.println("交换机名称："+exchange);
                System.out.println("路由键："+routingKey);
            }
        });

        rabbitTemplate.setMessageConverter(new MessageConverter() {
            @Override
            public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
                messageProperties.setContentType("text/xml");
                messageProperties.setContentEncoding("UTF-8");
                 Message message = new Message(JSON.toJSONBytes(o),messageProperties);
                System.out.println("调用了消息解析器");
                return message;
            }

            @Override
            public Object fromMessage(Message message) throws MessageConversionException {
                return null;
            }
        });
        return  rabbitTemplate;
    }

    @Bean
    public DirectExchange defaultExchange() {
//        return  new DirectExchange("my_rout_exchange",true,false,null);

        Map<String, Object> map = new HashMap<>();
        map.put("alternate-exchange","my_rout_exchange"); //备用交换机，已经存在的交换机
        return  new DirectExchange("directExchangeTest1",false,false,map);//声明的新的交换机

    }



}
