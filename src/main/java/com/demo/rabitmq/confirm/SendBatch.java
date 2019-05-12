package com.demo.rabitmq.confirm;

import com.demo.rabitmq.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName CommenSend
 * @Description
 *  批量发送： 发送n条后， 再确认
 *
 * @Author Administrator
 * @Date 2019/1/24 15:33
 * @Version 1.0
 */
public class SendBatch {

    private static final String QUEUE_NAME = "test_queue_confirm1";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //生产者调用confirmSelect ,将channel设置为confirm模式，  注意：该模式与 txSelect() 不可以同时设置。
        channel.confirmSelect();

        String magString = "hello common confirm  message!";
        for (int i=0;i<10;i++){
            channel.basicPublish("",QUEUE_NAME,null,magString.getBytes());
        }


        if(!channel.waitForConfirms()){
            System.out.println("message send failed");
        }else{
            System.out.println("message send ok");
        }
        channel.close();
        connection.close();


    }
}
