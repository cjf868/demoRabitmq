package com.demo.rabitmq.confirm;

import com.demo.rabitmq.utils.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName TxRecv
 * @Description TODO
 * @Author Administrator
 * @Date 2019/1/24 14:21
 * @Version 1.0
 */
public class RecvAsync {
    private static final String QUEUE_NAME = "test_queue_confirm3";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        channel.basicConsume(QUEUE_NAME,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("recv[common confirm] msg:"+new String(body,"utf-8"));
            }
        });



    }

}
