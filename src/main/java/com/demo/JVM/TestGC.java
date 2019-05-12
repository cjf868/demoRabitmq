package com.demo.JVM;

/**
 * @ClassName TestGC
 * @Description TODO
 * @Author Administrator
 * @Date 2019/5/7 15:02
 * @Version 1.0
 */
public class TestGC {

    public static void main(String[] args){
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a = 0 ;
       System.gc();
    }
}
