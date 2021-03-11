package demo.消息队列.简单队列;

import com.rabbitmq.client.*;
import demo.消息队列.服务连接.MQConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Customer {

    /** 队列名称 */
    private static final String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws TimeoutException, IOException {
         /** 1.获取连接 */
        Connection newConnection = MQConnectionUtils.newConnection();
        /** 2.获取通道 */
        Channel channel = newConnection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String msgString = new String(body, "UTF-8");
                System.out.println("消费者获取消息:" + msgString);
            }
        };
        /** 3.监听队列 */
        channel.basicConsume(QUEUE_NAME, true, defaultConsumer);

    }

}