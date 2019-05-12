package com.demo.yunsuanfu;

/**
 1.十进制转二进制

 原理：给定的数循环除以2，直到商为0或者1为止。将每一步除的结果的余数记录下来，然后反过来就得到相应的二进制了。

 比如8转二进制，第一次除以2等于4（余数0），第二次除以2等于2（余数0），第三次除以2等于1（余数0），最后余数1，得到的余数依次是0 0 0 1 ，

 反过来就是1000，计算机内部表示数的字节长度是固定的，比如8位，16位，32位。所以在高位补齐，java中字节码是8位的，所以高位补齐就是00001000.

 写法位（8）10=（00001000）2；

 代码实现：
 */

public class mapHashCodeTest {
    public static void main(String[] args) {
        String str = toBinary(8);
        System.out.println(str);
    }

    static String toBinary(int num) {
        String str = "";
        while (num != 0) {
            str = num % 2 + str;
            num = num / 2;
        }
        return str;
    }

}
