package com.demo.rabitmq.tx;

import com.demo.rabitmq.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName TxSend
 * @Description
 *  rabbitMq的消息确认机制（事物+confirm）
 *  持久化数据，解决rabitmaq服务 器异常的数据丢失的问题。
 *  问题：生产者将消息发送出去之后，消息到底有么有到达rabbitmq服务器，默认情况是不知道的；
 *
 *  解决： 两种方法
 *      AMQP实现了事物机制，类似mysql的事物
 *      confirm模式
        缺点：很耗时，降低了rabbitMq的消息吞吐量
 *
 *     AMQP事物机制：
 *     1、txSelect txCommit txRoollback
 *     txSelect:用于将当前channel设置成transation模式
 *     txCommit:提交事务
 *     txRoolback:回滚事物 * @Author Administrator
 * @Date 2019/1/24 14:15
 * @Version 1.0
 */
public class TxSend {
    private static final String QUEUE_NAME = "test_queue_tx";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        String magString = "hello tx message!" ;

        try {
            channel.txSelect();
            channel.basicPublish("",QUEUE_NAME,null,magString.getBytes());
            int a = 1/0; // 异常测试
            channel.txCommit();
            System.out.println("---send:"+magString);
        } catch (Exception e) {
            channel.txRollback();
            System.out.println("send message txRollback");
        }



        channel.close();
        connection.close();



    }

}
