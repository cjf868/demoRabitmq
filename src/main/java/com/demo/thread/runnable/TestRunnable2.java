package com.demo.thread.runnable;

/**
 * @ClassName TestRunnable2
 * @Description TODO
 * @Author Administrator
 * @Date 2019/5/7 13:38
 * @Version 1.0
 */
public class TestRunnable2 {
    public static void main(String[] args) {

        //测试Runnable
        MyThread2 t2 = new MyThread2();
        new Thread(t2,"线程1").start();//同一个t1，如果在Thread中就不行，会报错
        new Thread(t2,"线程2").start();
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

// 获取当前线程名称： Thread.currentThread().getName()
class MyThread2 implements Runnable{
    private int ticket = 3;

    @Override
    //记得要资源公共，要在run方法之前加上synchronized关键字，要不然会出现抢资源的情况
    //
    public    void  run() {
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

