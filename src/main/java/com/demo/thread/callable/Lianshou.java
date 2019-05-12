package com.demo.thread.callable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName Lianshou
 * @Description TODO
 * @Author Administrator
 * @Date 2019/5/7 19:53
 * @Version 1.0
 */
public class Lianshou {

    public static void main(String[] args)   {

        Callable<String > callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
               // Thread.sleep(1000);
                return "我是返回结果";
            }
        };

        FutureTask<String > futureTask = new FutureTask<>(callable);

        new Thread(futureTask).start();
        System.out.println(futureTask.isCancelled());
        futureTask.cancel(false);
        System.out.println(futureTask.isCancelled());
        try {
            if (!futureTask.isCancelled()) {
                System.out.println("begin");
                System.out.println(futureTask.isDone());
                System.out.println(futureTask.get()); //此方法会阻塞主进程的继续往下执行，如果不调用不会阻塞
                System.out.println(futureTask.isDone());
                System.out.println("end");
                System.out.println(futureTask.isCancelled());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }




    }



}
