package com.demo.thread.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 特性一：当池中正在运行的线程数（包括空闲线程）小于corePoolSize时，新建线程执行任务。
 下面用实验来说明，代码如下：
 结果：
 "C:\Program Files\Java\jdk1.8.0_181\bin\java.exe" -javaagent:D:\ide\ideaIU-2018.1.2\lib\idea_rt.jar=59644:D:\ide\ideaIU-2018.1.2\bin -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_181\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\rt.jar;D:\workspace\demo\target\classes;D:\soft\MVNRepository\org\springframework\boot\spring-boot-starter\2.1.2.RELEASE\spring-boot-starter-2.1.2.RELEASE.jar;D:\soft\MVNRepository\org\springframework\boot\spring-boot\2.1.2.RELEASE\spring-boot-2.1.2.RELEASE.jar;D:\soft\MVNRepository\org\springframework\spring-context\5.1.4.RELEASE\spring-context-5.1.4.RELEASE.jar;D:\soft\MVNRepository\org\springframework\spring-aop\5.1.4.RELEASE\spring-aop-5.1.4.RELEASE.jar;D:\soft\MVNRepository\org\springframework\spring-beans\5.1.4.RELEASE\spring-beans-5.1.4.RELEASE.jar;D:\soft\MVNRepository\org\springframework\spring-expression\5.1.4.RELEASE\spring-expression-5.1.4.RELEASE.jar;D:\soft\MVNRepository\org\springframework\boot\spring-boot-autoconfigure\2.1.2.RELEASE\spring-boot-autoconfigure-2.1.2.RELEASE.jar;D:\soft\MVNRepository\org\springframework\boot\spring-boot-starter-logging\2.1.2.RELEASE\spring-boot-starter-logging-2.1.2.RELEASE.jar;D:\soft\MVNRepository\ch\qos\logback\logback-classic\1.2.3\logback-classic-1.2.3.jar;D:\soft\MVNRepository\ch\qos\logback\logback-core\1.2.3\logback-core-1.2.3.jar;D:\soft\MVNRepository\org\apache\logging\log4j\log4j-to-slf4j\2.11.1\log4j-to-slf4j-2.11.1.jar;D:\soft\MVNRepository\org\apache\logging\log4j\log4j-api\2.11.1\log4j-api-2.11.1.jar;D:\soft\MVNRepository\org\slf4j\jul-to-slf4j\1.7.25\jul-to-slf4j-1.7.25.jar;D:\soft\MVNRepository\javax\annotation\javax.annotation-api\1.3.2\javax.annotation-api-1.3.2.jar;D:\soft\MVNRepository\org\springframework\spring-core\5.1.4.RELEASE\spring-core-5.1.4.RELEASE.jar;D:\soft\MVNRepository\org\springframework\spring-jcl\5.1.4.RELEASE\spring-jcl-5.1.4.RELEASE.jar;D:\soft\MVNRepository\org\yaml\snakeyaml\1.23\snakeyaml-1.23.jar;D:\soft\MVNRepository\com\rabbitmq\amqp-client\4.0.2\amqp-client-4.0.2.jar;D:\soft\MVNRepository\log4j\log4j\1.2.17\log4j-1.2.17.jar;D:\soft\MVNRepository\org\slf4j\slf4j-api\1.7.7\slf4j-api-1.7.7.jar;D:\soft\MVNRepository\org\slf4j\slf4j-log4j12\1.7.7\slf4j-log4j12-1.7.7.jar" com.demo.thread.threadpool.TestThreadPoolExecutor1
 -------------helloworld_001---------------pool-1-thread-1
 -------------helloworld_002---------------pool-1-thread-2

 实验结果分析：
 从实验结果上可以看出，当执行任务1的线程（thread-1）执行完成之后，任务2并没有去复用thread-1而是新建线程（thread-2）去执行任务。

 */
public class TestThreadPoolExecutor1 {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3, 60L, TimeUnit.SECONDS,new LinkedBlockingQueue<>(1));
        //任务1
        pool.execute(new Runnable() {

            @Override
            public void run() {
                System.out.println("-------------helloworld_001---------------" + Thread.currentThread().getName());
            }
        });

        try {
            //主线程睡2秒
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //任务2
        pool.execute(new Runnable() {

            @Override
            public void run() {
                System.out.println("-------------helloworld_002---------------" + Thread.currentThread().getName());
            }
        });
        pool.shutdown();

    }
}
