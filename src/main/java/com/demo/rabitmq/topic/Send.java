package com.demo.rabitmq.topic;

import com.demo.rabitmq.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.demo.rabitmq.publis_subscribe.Recv2.EXCHANGE_NAME;

/**
 * @ClassName Send
 * @Description
 *   topic Exchange：
 *   将路由键和某模式匹配
 *   “#” 匹配一个或多个
 *   “*” 匹配一个
 *
 *   eg: 商品：发布、删除、修改、删除
 *
 * @Author Administrator
 * @Date 2019/1/24 13:57
 * @Version 1.0
 */
public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_topic";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");

        String magString = "商品..." ;
        channel.basicPublish(EXCHANGE_NAME,"goods.add",null,magString.getBytes());
        channel.basicPublish(EXCHANGE_NAME,"goods.update",null,magString.getBytes());
        channel.basicPublish(EXCHANGE_NAME,"goods.delete",null,magString.getBytes());
        System.out.println("---send:"+magString);

        channel.close();
        connection.close();



    }


}
