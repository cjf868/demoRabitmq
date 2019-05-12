package com.demo.thread.callable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName CallableAndFuture2
 * @Description TODO
 * @Author Administrator
 * @Date 2019/4/26 22:58
 * @Version 1.0
 */
public class CallableAndFuture2 {
    public static void main(String[] args) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(6000);
                return new Random().nextInt();
            }
        };
        FutureTask<Integer> future = new FutureTask<>(callable);
        new Thread(future).start();

        try {
            Thread.sleep(1000);
            System.out.println("hello begin");
            System.out.println(future.isDone());
//            future.cancel(false);
            if (!future.isCancelled()) {
                System.out.println(future.get());
                System.out.println(future.isDone());
                System.out.println("hello end");
            } else {
                System.out.println("cancel~");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}