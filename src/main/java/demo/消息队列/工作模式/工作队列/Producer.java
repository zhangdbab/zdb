package demo.消息队列.工作模式.工作队列;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import demo.消息队列.服务连接.MQConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * @desc:    工作队列——生产者
 */
public class Producer {

    /** 队列名称 */
    private static final String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        /** 1.获取连接 */
        Connection newConnection = MQConnectionUtils.newConnection();
         /** 2.创建通道 */
        Channel channel = newConnection.createChannel();
         /**3.创建队列声明 */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
         /**Queue每次给每个消费者发送一条消息；消费者处理完这条消息后Queue会再给该消费者发送一条消息 */
        /*多个consumer时默认是轮流分发，造成消费快的consumer提前完成任务，造成资源的浪费
        设置basicQos之后，达到“能者多劳”的效果。消费快的消费更多的消息，提高效率*/
        channel.basicQos(1);
        for (int i = 1; i <= 50; i++) {
            String msg = "生产者消息_" + i;
            System.out.println("生产者发送消息:" + msg);
            /**4.发送消息 */
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
        }
        channel.close();
        newConnection.close();
    }

}
