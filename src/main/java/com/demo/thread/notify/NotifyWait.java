package com.demo.thread.notify;

/**
 * @ClassName NotifyWait
 * @Description TODO
 * @Author Administrator
 * @Date 2019/5/7 14:30
 * @Version 1.0
 */

// 打印 1-52 ，A-Z   要求： 1 2 A 3 4 B …… Z
public class NotifyWait {
    public static void main(String[] args) {
        Print print = new Print();

        // 最简单的代码实现方式
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 26; i++) {
                    try {
                        print.printNumber();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 26; i++) {
                    try {
                        print.printAZ(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

class Print { //设置条件保证先打印数字
    private boolean flag = true;
    //这个是要打印数字的起始
    private int number = 0;

    public synchronized void printNumber() throws InterruptedException {
        if (!flag) {
            this.wait();
        }
        for (int i = 0; i < 2; i++) {
            System.out.println(++number + "\t");
        }
        flag = false;
        this.notify();
    }

    public synchronized void printAZ(int i) throws InterruptedException {
        if (flag) {
            this.wait();
        }

        System.out.println((char) ('A' + i) + "\t");

        flag = true;

        this.notify();
    }
}
