package com.demo.thread.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 特性四：当队列里的任务数达到上限，并且池中正在运行的线程数等于maximumPoolSize，对于新加入的任务，执行拒绝策略（线程池默认的拒绝策略是抛异常）。
 下面用实验来说明，代码如下：
 结果：
 Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task com.demo.thread.threadpool.TestThreadPoolExecutor4$5@3fee733d rejected from java.util.concurrent.ThreadPoolExecutor@5acf9800[Running, pool size = 3, active threads = 3, queued tasks = 1, completed tasks = 0]
 at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2063)
 at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:830)
 at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1379)
 at com.demo.thread.threadpool.TestThreadPoolExecutor4.main(TestThreadPoolExecutor4.java:74)
 -------------helloworld_004---------------pool-1-thread-3
 -------------helloworld_003---------------pool-1-thread-3
 -------------helloworld_001---------------pool-1-thread-1


 实验结果分析：
 当任务5加入时，队列达到上限，池内运行的线程数达到最大，故执行默认的拒绝策略，抛异常。
 本文中使用到的队列类型虽然仅限于LinkedBlockingQueue这一种队列类型，但总结出来的特性，对与常用ArrayBlockingQueue 和 SynchronousQueue同样适用，
 些许不同及三种队列的区别，将在下个章节中说明。
 */

public class TestThreadPoolExecutor4 {

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
        // 任务1
        pool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3 * 1000);
                    System.out.println("-------------helloworld_001---------------" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 任务2
        pool.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(5 * 1000);
                    System.out.println("-------------helloworld_002---------------" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 任务3
        pool.execute(new Runnable() {

            @Override
            public void run() {
                System.out.println("-------------helloworld_003---------------" + Thread.currentThread().getName());
            }
        });

        // 任务4
        pool.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("-------------helloworld_004---------------" + Thread.currentThread().getName());
            }
        });

        // 任务5
        pool.execute(new Runnable() {

            @Override
            public void run() {
                System.out.println("-------------helloworld_005---------------" + Thread.currentThread().getName());
            }
        });
        pool.shutdown();
    }

}