package com.demo.thread.threadpool;

import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName LianShou
 * @Description TODO
 * @Author Administrator
 * @Date 2019/5/7 21:53
 * @Version 1.0
 */
public class LianShou {

    public static void main(String[] args){

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
                System.out.println("-------------helloworld_004---------------" + Thread.currentThread().getName());
            }
        });


    }
}
