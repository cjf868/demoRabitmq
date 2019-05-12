package com.demo.thread.runnable;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName TestRunnable3
 * @Description TODO
 * @Author Administrator
 * @Date 2019/5/7 13:49
 * @Version 1.0
 */
public class TestRunnable3 {

    public static void main(String[] args) {

        //测试Runnable
        MyThread3 t = new MyThread3();
        new Thread(t,"线程1").start();//同一个t1，如果在Thread中就不行，会报错
        new Thread(t,"线程2").start();
        new Thread(t,"线程3").start();
        // new Thread(t1).start();


        /**
         * 输出结果，没有同步，线程不安全， 1 重复了
         * 线程1:卖票：ticket3
         * 线程2:卖票：ticket2
         * 线程2:卖票：ticket1
         * 线程1:卖票：ticket1
         */
    }

}

// 获取当前线程的引用和名称： Thread.currentThread().getName()
class MyThread3 implements Runnable{

    private int ticket = 3;

    //创建一个锁对象
    private final Lock lock = new ReentrantLock();



    //记得要资源公共，要在run方法之前加上synchronized关键字，要不然会出现抢资源的情况
    @Override
    public   void  run() {
       method();
    }



    // run 执行的方法体
    private void method(){
        //进入代码块之后立马加锁  , 感觉效率跟 synchronized 一样
        lock.lock();//获取锁
        for (int i = 0; i <3; i++) {
            if (this.ticket>0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+":卖票：ticket"+this.ticket--);
            }
        }

    }
}
