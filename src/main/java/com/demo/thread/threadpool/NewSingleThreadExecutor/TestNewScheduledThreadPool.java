package com.demo.thread.threadpool.NewSingleThreadExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 (3) newScheduledThreadPool
 创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：
 */
public class TestNewScheduledThreadPool {

    public static void main(String[] args){


        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {

            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);
    }
}
