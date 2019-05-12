package com.demo.rabitmq.workfair;

import com.demo.rabitmq.utils.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Recv2
 * @Description
 * @Author Administrator
 * @Date 2019/1/21 19:03
 * @Version 1.0
 */
public class Recv2 {


    private static  final  String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        //获取连接
        Connection connection = ConnectionUtils.getConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //声明要关注的队列
        //创建队列声明 ：第二个参数durable持久化
        boolean duralbe = true;
        channel.queueDeclare(QUEUE_NAME, duralbe, false,false, null);

        /**
         * 每个消费者发送确认消息之前，消息队列不发送下一个消息到消费者，一次只处理一个消息
         * 限制每次发送给同一个消费者不得超过一条消息
         */
        int prefetchCount = 1 ;
        channel.basicQos(prefetchCount);

        //DefaultConsumer类实现了Consumer接口，通过传入一个频道，
        // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
       Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("{2} recv msg:"+msg);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("{2} done");
                    //手动发送回执
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };


        //自动回复队列应答 -- RabbitMQ中的消息确认机制
        boolean autoAck = false; // 关闭自动应答 ,改为false
        channel.basicConsume(QUEUE_NAME,autoAck,consumer);



    }
}
