package com.demo.rabitmq.workfair;

import com.demo.rabitmq.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Send
 * @Description
 *
 *                                 |------c1
 *               p-------Queue-----|
 *                                 |------c2
 *
 *
 *
 *
 *               2、公平分发：fair dispach ,能者多劳
 *                    1、使用basicQos(perfetch=1) ,限制每次发送一条；
 *                     消费者需要手动反馈，我消费完了。收到请求后，队列会再次发送下一个请求；
 *                    2、需要关闭自动应答，autoAck = false
 *
 *
 *               boolean autoAck = true ;自动确认模式，一旦队列把消息发送给消费者，就会从内存中删除，
 *               如果删除正在处理的消费者，则会丢失数据。
 *               防止消息丢失，autoAck = false ，手动模式，如果有一个消费者挂掉了，队列收不到回执，队列会把消息发送给其他消费者。
 *               rabbitMq支持消息应答，消费者发送回执应答队列，消息已经处理，可以删除了。然后，rabbitMq才会删除内存中消息。
 *
 *               消息应答默认是打开的，acknowlegement ,简写Ack
 *
 *               如果rabbitMq服务挂了，队列中未发送的消息，一样会丢失。
 *               解决：消息的持久化 durable
 *               声明队列的第二个参数：durable = ture;
 *
 *
 *
 * @Author Administrator
 * @Date 2019/1/21 18:57
 * @Version 1.0
 */
public class Send {
    private static  final  String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        //获取连接
        Connection  connection = ConnectionUtils.getConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //创建队列声明 ：第二个参数durable持久化
        boolean duralbe = true;
        channel.queueDeclare(QUEUE_NAME,duralbe,false,false,null);

        /**
         * 每个消费者发送确认消息之前，消息队列不发送下一个消息到消费者，一次只处理一个消息
         * 限制每次发送给同一个消费者不得超过一条消息
         */
        int prefetchCount = 1 ;
        channel.basicQos(prefetchCount);;
        for (int i=0;i<50;i++){
            String msg = "hello work:"+i;
            //发送消息
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            System.out.println("--send msg:"+msg);
            Thread.sleep(i*2);

        }


        channel.close();

        connection.close();
    }
}
