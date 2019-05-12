package com.demo.thread.callable;

/**
 * @ClassName CallableAndFuture
 * @Description TODO
 * @Author Administrator
 * @Date 2019/4/26 22:48
 * @Version 1.0
 */

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableAndFuture {
    public static void main(String[] args) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(6000);
                System.out.println("开始了");
                return new Random().nextInt();
            }
        };

        FutureTask<Integer> future = new FutureTask<>(callable);
        new Thread(future).start();

        try {
            Thread.sleep(1000);
            System.out.println("hello begin");
            System.out.println(future.isDone());
            System.out.println(future.get()); //此方法会阻塞主进程的继续往下执行，如果不调用不会阻塞
            System.out.println(future.isDone());
            System.out.println("hello end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}