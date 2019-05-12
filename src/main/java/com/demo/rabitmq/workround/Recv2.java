package com.demo.rabitmq.workround;

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


    private static  final  String QUEUE_NAME = "test_work_queue_round";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        //获取连接
        Connection connection = ConnectionUtils.getConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //声明要关注的队列
        channel.queueDeclare(QUEUE_NAME, false, false,false, null);

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
                }
            }
        };


        //自动回复队列应答 -- RabbitMQ中的消息确认机制
        boolean autoAck = true;
        channel.basicConsume(QUEUE_NAME,autoAck,consumer);



    }
}
