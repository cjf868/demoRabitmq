package com.demo.thread.threadpool.NewSingleThreadExecutor;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 单个线程的线程池，即线程池中每次只有一个线程工作，单线程串行执行任务
 * 看源码就懂：
 * public static ExecutorService newSingleThreadExecutor() {
 *         return new FinalizableDelegatedExecutorService
 *             (new ThreadPoolExecutor(1, 1,
 *                                     0L, TimeUnit.MILLISECONDS,
 *                                     new LinkedBlockingQueue<Runnable>()));
 *     }
 */
public class ThreadPoolByNewSingleThreadExecutor {

    public static void main(String[] args) {
        /**
         * 单线程化的线程池
         */
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    Thread.currentThread().setName("Thread i = " + index);
                    System.out.println(Thread.currentThread().getName() + " index = " + index);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println("ssss");
                    }
                }
            });
        }
        singleThreadExecutor.shutdown();
        System.out.println("on the main thread...");

    }

}
