package com.demo.thread.runnable;

/**
 * @ClassName TestRunnable
 * @Description TODO
 * @Author Administrator
 * @Date 2019/5/7 0:19
 * @Version 1.0
 */
public class TestRunnable {

    public static void main(String[] args) {

        //测试Runnable
        MyThread1 t1 = new MyThread1();
        new Thread(t1).start();//同一个t1，如果在Thread中就不行，会报错 ，同步机制，
        new Thread(t1).start();
       // new Thread(t1).start();


    }

}
class MyThread1 implements Runnable{
    private int ticket = 3;

    @Override
    //记得要资源公共，要在run方法之前加上synchronized关键字，要不然会出现抢资源的情况
    // 同步机制，性能降低了，没啥意义
    public synchronized  void  run() {
        for (int i = 0; i <3; i++) {
            if (this.ticket>0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("卖票：ticket"+this.ticket--);
            }
        }

    }
}
