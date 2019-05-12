package com.demo.thread.callable;

/**
 * @ClassName CallableThreadTest
 * @Description TODO
 * @Author Administrator
 * @Date 2019/4/26 22:53
 * @Version 1.0
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableThreadTest implements Callable<Integer> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableThreadTest ctt = new CallableThreadTest();
        FutureTask<Integer> ft = new FutureTask<>(ctt);
        new Thread(ft, "有返回值的线程").start();

        System.out.println("子线程的返回值" + ft.get());
    }

    @Override
    public Integer call() {
        int i;
        for (i = 0; i < 10; i += 2) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return i;
    }
}