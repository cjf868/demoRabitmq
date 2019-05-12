package com.demo.rabitmq.confirm;

import com.demo.rabitmq.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName CommenSend
 * @Description
 *     Confrim模式：
 *  *
 *  *   生产者端confirm模式的实现原理
 *  *   生产者将信道设置成confirm模式，一旦信道进入confirm模式，所有再该信道上面发布的消息都会
 *  *   被指派一个唯一的ID（从1开始），一旦消息被投递到所有匹配的队列之后，broker就会发送一个确认给
 *  *   生产者（包含消息的唯一ID),这就使得生产者知道消息已经正确到达目的队列了，如果消息和队列是可持久化的，
 *  *   那么确认消息会将消息写入磁盘之后发出，broker回传给生产者的确认消息中deliver-tag域包含了确认
 *  *   消息的序列号，此外broker也可以设置basic.ack的multiple域，表示到这个序号之前的所有消息都已经得到了处理。
 *  *
 *  *
 *  *   confirm模式的最大好处在于 他是异步；
 *  *   nack
 *  *
 *  *   confirm模式开启：channel.confirmSelect(); 有三种编程模式
 *  *   1、普通 waitForConfirms() ,发一条
 *  *   2、批量的，发一批 ，waitForConfirmms
 *  *   3、异步confirm 模式，提供一个回调方法
 *
 * @Author Administrator
 * @Date 2019/1/24 15:33
 * @Version 1.0
 */
public class SendCommen {

    private static final String QUEUE_NAME = "test_queue_confirm1";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //生产者调用confirmSelect ,将channel设置为confirm模式，  注意：该模式与 txSelect() 不可以同时设置。
        channel.confirmSelect();

        String magString = "hello common confirm  message!";
        channel.basicPublish("",QUEUE_NAME,null,magString.getBytes());

        if(!channel.waitForConfirms()){
            System.out.println("message send failed");
        }else{
            System.out.println("message send ok");
        }
        channel.close();
        connection.close();


    }
}
