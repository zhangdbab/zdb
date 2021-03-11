package demo.消息队列.路由模式;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import demo.消息队列.服务连接.MQConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * @desc:    路由模式——生产者
 *  
 *
 */
public class ProdecerRouting {

    private static final String EXCHANGE_NAME = "my_rout_exchange";
    public static void main(String[] args) throws IOException, TimeoutException {
        /** 1.创建新的连接 */
        Connection connection = MQConnectionUtils.newConnection();
        /** 2.创建通道 */
        Channel channel = connection.createChannel();
        /** 3.绑定的交换机 参数1交互机名称 参数2 exchange类型 */
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        /** 4.发送消息 */
        String message = "",sendType="";
        for (int i = 0; i < 10; i++)
        {
            if(i%2==0){
                sendType = "info";
                message = "我是 info 级别的消息类型：" + i;
            }else{
                sendType = "error";
                message = "我是 error 级别的消息类型：" + i;
            }
            System.out.println("[send]：" + message + "  " +sendType);
            channel.basicPublish(EXCHANGE_NAME, sendType, null, message.getBytes("utf-8"));
            try {
                Thread.sleep(5 * i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /** 5.关闭通道、连接 */
        channel.close();
        connection.close();
        /** 注意：如果消费没有绑定交换机和队列，则消息会丢失 */
    }

}
