package com.demo.rabitmq.routing;

import com.demo.rabitmq.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Send
 * @Description
 *
 *    路由模式：routing
 *    Exchange(交换机，转发器) 一方面是接手生产者的消息，另一方面向队列推送消息
 *    交换机类型：Fanout (不处理路由键) ，只要绑定的消费者，都能接收
 *              basicPbulish(),第二个参数routingKey 为空
 *
 *              Direct (路由处理模式)
 *
 * @Author Administrator
 * @Date 2019/1/22 18:34
 * @Version 1.0
 */
public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        String msg = "hell direct";

        String routingKey = "error";
        channel.basicPublish(EXCHANGE_NAME,routingKey,null,msg.getBytes());
        System.out.println("send :"+msg
        );
        channel.close();
        connection.close();
    }
}
