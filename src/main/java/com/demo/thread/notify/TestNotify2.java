package com.demo.thread.notify;

/**
 * @ClassName TestNotify
 * @Description TODO
 * @Author Administrator
 * @Date 2019/5/7 15:32
 * @Version 1.0
 */
public class TestNotify2 {


    public static void main(String[] args) {
        Print2 p = new Print2();

        PrintN pn = new PrintN(p);
        PrintZ pz = new PrintZ(p);
        new Thread(pn).start();
        new Thread(pz).start();
    }

}


// 实现方式写，代码比较复杂
class PrintN implements Runnable {
    private Print2 p;

    PrintN(Print2 p) {
        this.p = p;
    }

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
}

class PrintZ implements Runnable {
    private Print2 p;

    PrintZ(Print2 p) {
        this.p = p;
    }

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
}


class Print2 {

    private boolean flag = true; // 锁标识用

    private int num = 0; //打印的数字起始

    public synchronized void printNum() throws InterruptedException {
        if (!flag) {
            this.wait();
        }

        for (int i = 0; i < 2; i++) {
            System.out.println(++num);
        }
        flag = false;
        this.notify();
    }


    public synchronized void printZm(int i) throws InterruptedException {

        if (flag) {
            this.wait();
        }

        System.out.println((char) ('A' + i) + "\t");
        flag = true;
        this.notify();


    }
}

