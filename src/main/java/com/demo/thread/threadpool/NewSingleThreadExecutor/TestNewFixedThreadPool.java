package com.demo.thread.threadpool.NewSingleThreadExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。示例代码如下：
 */
public class TestNewFixedThreadPool {

    public static void main(String[] args){

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName()+"-----"+index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });

        }
        fixedThreadPool.shutdown();
    }

}
