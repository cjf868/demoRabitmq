package com.demo.rabitmq.simple;

import com.demo.rabitmq.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Send
 * @Description  简单队列发送消息 ： 耦合性高，一对一，多个消费者不支持
 * @Author Administrator
 * @Date 2019/1/21 18:57
 * @Version 1.0
 */
public class Send {
    private static  final  String QUEUE_NAME = "test_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {


        Connection connection = ConnectionUtils.getConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        String msg = "hello simple";

        //发送消息
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());

        System.out.println("--send msg:" + msg);
        channel.close();

        connection.close();
    }
}
