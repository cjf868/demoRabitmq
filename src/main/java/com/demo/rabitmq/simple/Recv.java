package com.demo.rabitmq.simple;

import com.demo.rabitmq.utils.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Recv
 * @Description 简单队列接收消息
 * @Author Administrator
 * @Date 2019/1/21 19:03
 * @Version 1.0
 */
public class Recv {


    private static  final  String QUEUE_NAME = "test_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Recv.newApi();
        Recv.oldApi();



    }


    public static void newApi() throws IOException, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        DefaultConsumer consumer =  new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
               String msgString = new String(body,"utf-8");
                System.out.println("new api recv :"+msgString);
            }
        };

        //监听队列，类似android
        channel.basicConsume(QUEUE_NAME,true,consumer);

    }


    public static  void  oldApi() throws IOException, TimeoutException, InterruptedException {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();

        //创建频道
        Channel channel = connection.createChannel();

        //定义队列的消费者（过时的写法，比较简单）
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);

        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();

            String msgString = new String(delivery.getBody());

            System.out.println("old apid recv:"+msgString);
        }

    }
}
