package com.demo.rabitmq.workround;

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
 *               1、轮询分发（round-robin）:任务均分
 *
 * @Author Administrator
 * @Date 2019/1/21 18:57
 * @Version 1.0
 */
public class Send {
    private static  final  String QUEUE_NAME = "test_work_queue_round";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        //获取连接
        Connection  connection = ConnectionUtils.getConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //创建队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);



        for (int i=0;i<50;i++){
            String msg = "hello work:"+i;
            //发送消息 ，第一个参数为空，表明是匿名发送
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            System.out.println("--send msg:"+msg);
            Thread.sleep(i*20);

        }


        channel.close();

        connection.close();
    }
}
