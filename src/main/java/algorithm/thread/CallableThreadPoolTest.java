package algorithm.thread;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gaolei
 * @description 使用线程池工厂以及遵循Java开发手册标准测试线程
 * @date 2020/11/26 17:19
 */
public class CallableThreadPoolTest {
    /**
     * 线程前缀
     */
    final String NAME_PREFIX = "test-thread";
    /**
     * 线程计数器
     */
    AtomicInteger threadNumber = new AtomicInteger(1);

    /**
     * 任务计数器
     */
    AtomicInteger taskNumber = new AtomicInteger(1);

    ThreadFactory threadFactory = r -> new Thread(r, NAME_PREFIX + threadNumber.getAndIncrement());

    /**
     * 核心线程数1，最大线程数5，非核心线程空闲时间最大1s
     * 阻塞队列最大为1000，拒绝策略让调用的线程处理
     */
    ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 5, 1L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000),  threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

    public Integer runTask () throws InterruptedException {
        int random = new Random().nextInt(2000) + 3000;
        System.out.println("线程"+ taskNumber.getAndIncrement() +"开始运行结束:" + Thread.currentThread().getName());
        return taskNumber.get();
    }

    public Future<Integer> runAysn() throws InterruptedException {
        Callable<Integer> callableRun = () -> runTask();
        return executor.submit(callableRun);
    }

    @Test
    public void startThreadPool() throws InterruptedException {
        for (int i =0; i < 1000; i++) {
            runAysn();
        }
        while (executor.getActiveCount() > 0) {
            Thread.sleep(10000);
        }
    }
}
