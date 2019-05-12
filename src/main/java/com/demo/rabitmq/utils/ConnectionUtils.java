package com.demo.rabitmq.utils;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName ConnectionUtils
 * @Description TODO
 * @Author Administrator
 * @Date 2019/1/21 18:47
 * @Version 1.0
 */
public class ConnectionUtils {

    public static Connection getConnection() throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        //设置服务地址
        factory.setHost("127.0.0.1");

        //设置库
        factory.setPort(5672);

        factory.setVirtualHost("vhost_test");

        factory.setUsername("test");

        factory.setPassword("1234");

        return factory.newConnection();


    }
}
