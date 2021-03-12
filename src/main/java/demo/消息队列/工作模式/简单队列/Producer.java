package demo.消息队列.工作模式.简单队列;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import demo.消息队列.服务连接.MQConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    /** 队列名称 */
    private static final String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws IOException, TimeoutException, IOException, TimeoutException {
        /** 1.获取连接 */
        Connection newConnection = MQConnectionUtils.newConnection();
        /** 2.创建通道 */
        Channel channel = newConnection.createChannel();
        /** 3.创建队列声明 */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String msg = "我是生产者生成的消息";
        System.out.println("生产者发送消息:" + msg);
        /** 4.发送消息 */
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        channel.close();
        newConnection.close();
    }
}