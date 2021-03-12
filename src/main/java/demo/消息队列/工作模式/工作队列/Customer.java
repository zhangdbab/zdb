package demo.消息队列.工作模式.工作队列;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import demo.消息队列.服务连接.MQConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * @desc:    工作队列——消费者
 */
public class Customer {

    /**
     * 队列名称
     */
    private static final String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        /** 1.获取连接 */
        Connection newConnection = MQConnectionUtils.newConnection();
        /** 2.获取通道 */
        final Channel channel = newConnection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        /** Queue每次给每个消费者发送一条消息；消费者处理完这条消息后Queue会再给该消费者发送一条消息 */
       /* 多个consumer时默认是轮流分发，造成消费快的consumer提前完成任务，造成资源的浪费
        设置basicQos之后，达到“能者多劳”的效果。消费快的消费更多的消息，提高效率*/
        channel.basicQos(1);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String msgString = new String(body, "UTF-8");
                System.out.println("消费者获取消息:" + msgString);
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {

                } finally {
                    /** 手动回执消息 */
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        /** 3.监听队列 */
        channel.basicConsume(QUEUE_NAME, false, defaultConsumer);
    }

}
