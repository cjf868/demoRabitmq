
多线程开发

为了更好的使用多线程，JDK提供了线程池供开发人员使用，目的在于减少线程的创建和销毁次数，以此达到线程的重复利用。

ThreadPoolExecutor -> AbstractExceutorService -> ExecutorService -> Executor
class extends  class implements interface extends interface

其中ThreadPoolExecutor是线程池中最核心的一个类，我们先简单看一下这个类的继承关系。

 其中Executor是线程池的顶级接口，接口中只定义了一个方法  void execute(Runnable command)；
 线程池的操作方法都是定义子在ExecutorService子接口中的，所以说ExecutorService是线程池真正的接口。

ThreadPoolExecutor提供了四个构造方法，我们看一下参数最全的一个构造函数；

public ThreadPoolExecutor(int corePoolSize,
            　　　　　　　　　　　int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
　　　　　　　　　　　　　　　　　　ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {

}
函数的参数含义如下：

    corePoolSize： 线程池核心线程数
    maximumPoolSize：线程池最大数
    keepAliveTime： 空闲线程存活时间
    unit： 时间单位
    workQueue： 线程池所使用的缓冲队列
    threadFactory：线程池创建线程使用的工厂
    handler： 线程池对拒绝任务的处理策略

本节我们主要对前五个参数中的corePoolSize，maximumPoolSize及workQueue是如何配合使用做出说明（keepAliveTime，unit主要对空闲线程的存活时间做的定义，见名知意，不再做出说明），以此来引出线程池的一些特性。

threadFactory和handler这两个参数都有默认值，对于它们的用法将放到其它章节去做说明。

见：
TestThreadPoolExecutor1 TestThreadPoolExecutor2 TestThreadPoolExecutor3 TestThreadPoolExecutor4 测试