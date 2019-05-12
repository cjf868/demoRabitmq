package com.demo.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName test1
 * @Description TODO
 * @Author Administrator
 * @Date 2019/4/26 23:30
 * @Version 1.0
 */
public class test1 {

    static boolean foo(char c) {
        System.out.print(c);
        return true;
    }

    public static void main(String[] argv) {
        int i = 0;
        //for(65;88&&(i<2);67)
        for (foo('A'); foo('B') && (i < 2); foo('C')) {
            i++;
            foo('D');
        }
    }

    public void testList() {
        List a = new ArrayList();

        ArrayList b = new ArrayList();

    }


}
