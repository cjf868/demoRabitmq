package com.demo.thread.threadpool.NewSingleThreadExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**

 newCachedThreadPool
 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。示例代码如下：

 源码：
 public static ExecutorService newCachedThreadPool() {
 return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
 60L, TimeUnit.SECONDS,
 new SynchronousQueue<Runnable>());
 }

 */
public class ThreadPoolByNewCachedThreadPool {

    public static void main(String[] args){

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cachedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }
        cachedThreadPool.shutdown();
    }
}
