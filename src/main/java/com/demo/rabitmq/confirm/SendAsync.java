package com.demo.rabitmq.confirm;

import com.demo.rabitmq.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName CommenSend
 * @Description
 *  异步模式：
 *  Channel对象提供ConfirmListener()回调方法只包含deliverTag (当前Channel发送的消息序号），我们需要自己为
 *  每一个Channel维护一个unconfirm的消息序号集合，没publish一条数据，集合中元素加1，每回调一次handleAck方法，
 *  unconfirm集合删掉相应的一条（multiple=false)或多条（multiple=true）记录，从程序运行效率上看，这个unconfirm
 *  集合最好采用有序集合SortedSet存储结构。
 *
 *
 *
 * @Author Administrator
 * @Date 2019/1/24 15:33
 * @Version 1.0
 */
public class SendAsync {

    private static final String QUEUE_NAME = "test_queue_confirm3";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //生产者调用confirmSelect ,将channel设置为confirm模式，  注意：该模式与 txSelect() 不可以同时设置。
        channel.confirmSelect();

        //存放的是未确认的 消息标识tag
        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());

        //添加监听通道
        channel.addConfirmListener(new ConfirmListener() {

            //没有问题的 handleAck
            @Override
            public void handleAck(long deleveryTag, boolean multiple) throws IOException {  //multiple :true 多条， false单条
                if(multiple){
                    System.out.println("------handleAck------multiple  ");
                    confirmSet.headSet(deleveryTag+1).clear();
                }else{
                    System.out.println("------handleAck------multiple false");
                    confirmSet.remove(deleveryTag);
                }
            }

            //有问题的
            @Override
            public void handleNack(long deleveryTag, boolean multiple) throws IOException {
                if(multiple){
                    System.out.println("------handleNack------multiple  ");
                    confirmSet.headSet(deleveryTag+1).clear();
                }else{
                    System.out.println("------handleNack------multiple false");
                    confirmSet.remove(deleveryTag);
                }

            }
        });

        String magString = "hello async confirm  message!";

        while(true){
            long seqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("",QUEUE_NAME,null,magString.getBytes());
            confirmSet.add(seqNo);
        }



    }
}
