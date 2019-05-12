package com.demo.arithmetic;

/**
 * @ClassName Sort
 * @Description TODO
 * @Author Administrator
 * @Date 2019/5/6 12:32
 * @Version 1.0
 */
public class sort {



    public static void main(String[] args){
      int[] a =  new int[]{3,34,23,42,341,325,2143};
        bubbleSort(a);


    }

    public static void bubbleSort(int[] a) {
        int len = a.length;
        for (int i = 1; i < len - 1; i++) {
            for (int j = i; j < len - i; j++) {
                if (a[j + 1] < a[j]) {
                    swap(a, j + 1, j);
                }
            }
        }

        for (int b:a){
            System.out.println(b);
        }
    }

    //交换
    private static void swap(int[] a, int i, int j) {
        a[i] ^= a[j];
        a[j] ^= a[i];
        a[i] ^= a[j];
    }



}
