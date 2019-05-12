package com.demo.thread.notify;

/**
 * @ClassName TestNotify
 * @Description TODO
 * @Author Administrator
 * @Date 2019/5/7 15:32
 * @Version 1.0
 */
public class TestNotify1 {


    public static void main(String[] args) {
        Print1 p = new Print1();

         Runnable r1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 26; i++) {
                    try {
                        p.printNum();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


       // 直接new runnable 代码比较简练
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 26; i++) {
                    try {
                        p.printZm(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(r1).start();
        new Thread(r2).start();


        // 该方式 代码更简单
        /*new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();*/

    }

}



    class   Print1{

        private  boolean flag = true; // 锁标识用

        private  int num=0; //打印的数字起始
        public   synchronized  void printNum() throws InterruptedException {
            if(!flag){
                this.wait();
            }

            for(int i = 0 ;i<2 ;i++){
                System.out.println(++num);
            }
            flag = false;
            this.notify();
        }


        public   synchronized  void printZm(int i) throws InterruptedException {

            if(flag){
                this.wait();
            }

            System.out.println((char) ('A' + i) + "\t");
            flag = true;
            this.notify();


        }
    }

