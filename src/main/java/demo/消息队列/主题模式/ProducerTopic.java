package demo.消息队列.主题模式;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import demo.消息队列.服务连接.MQConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * @author: niceyoo
 * blog:    https://cnblogs.com/niceyoo
 * desc:
 */

public class ProducerTopic {

    private static final String EXCHANGE_NAME = "my_topic_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        /** 1.创建新的连接 */
        Connection connection = MQConnectionUtils.newConnection();
        /** 2.创建通道 */
        Channel channel = connection.createChannel();
        /** 3.绑定的交换机 参数1交互机名称 参数2 exchange类型 */
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        /** 4.发送消息 */
        String routingKey = "log.info.error";
        String msg = "topic_exchange_msg：" + routingKey;
        System.out.println("[send] = " + msg);
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, msg.getBytes());
        /** 5.关闭通道、连接 */
        channel.close();
        connection.close();
        /** 注意：如果消费没有绑定交换机和队列，则消息会丢失 */
    }

}
