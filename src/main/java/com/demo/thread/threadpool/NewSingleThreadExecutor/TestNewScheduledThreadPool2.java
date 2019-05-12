package com.demo.thread.threadpool.NewSingleThreadExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 表示延迟1秒后每3秒执行一次。

 ScheduledExecutorService比Timer更安全，功能更强大，后面会有一篇单独进行对比。
 */
public class TestNewScheduledThreadPool2 {

    public static void main(String[] args){
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }
}
