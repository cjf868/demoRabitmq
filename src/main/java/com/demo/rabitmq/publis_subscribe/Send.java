package com.demo.rabitmq.publis_subscribe;

import com.demo.rabitmq.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Send
 * @Description
 *    ------------------------------------------
 *  *               订阅模式：publish/subscribe  发布/订阅
 *  *               1、一个生产者，多个消费者
 *  *               2、没一个消费者有自己的队列
 *  *               3、生产者把消息发送个交换机，交换机，转发到队列
 *  *               4、每个队列都要绑定到交换机
 *  *               5、生产者发送的消息经过交换机，到达队列，就能实现一个消息被多个消费者消费。
 *  *
 *  *               eg: 注册之后：1、发邮件 2、发短信。
 *
 *                  exchange没有存储能力，只有队列有存储能力；
 *
 * @Author Administrator
 * @Date 2019/1/22 18:18
 * @Version 1.0
 */
public class Send {

    public static final String EXCHANGE_NAME = "test_exchange_fanout";
    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        //发送消息
        String msg = "hello ps";

        channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes()); //第二个参数routingKey 为空,不处理路由键

        System.out.println("Ps Send:"+msg);

        channel.close();
        connection.close();
    }
}
