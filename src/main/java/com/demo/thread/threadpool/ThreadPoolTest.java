package com.demo.thread.threadpool;



import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的用法
 */
public class ThreadPoolTest {

    public static void main(String[] args) {

        Map<String,Object> a = new HashMap<String ,Object>();

        List<String> strList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            strList.add("String" + i);
        }
        int threadNum = strList.size() < 20 ? strList.size() : 20;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, threadNum, 300,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(3),
                new ThreadPoolExecutor.CallerRunsPolicy());

        long begin = new Date().getTime(); //开始时间

        for (int i = 0; i < threadNum; i++) {
            executor.execute(new PrintStringThread(i,strList,threadNum));
        }

        executor.shutdown();
        long end = new Date().getTime();
        System.out.println("耗时："+(end-begin) );

    }
}

class PrintStringThread implements Runnable {

    private int num;

    private List<String> strList;

    private int threadNum;

    public PrintStringThread(int num, List<String> strList, int threadNum) {
        this.num = num;
        this.strList = strList;
        this.threadNum = threadNum;
    }

    public synchronized void run() {
        int length = 0;
        for(String str : strList){

            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            if (length % threadNum == num) { //余数取整
                System.out.println(length+":"+threadNum+":线程编号：" + num + "，字符串：" + str+" "+ new Date());
            }
            length ++;
        }
    }
}