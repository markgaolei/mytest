package algorithm.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author gaolei
 * @description 线程池测试
 * @date 2020/11/23 19:49
 */
public class ThreadPoolTest {
    class ThreadPoolCallable implements Callable<Integer> {
        private volatile Integer number = 0;

        @Override
        public Integer call() throws Exception {
            System.out.println("线程启动");
            Thread.sleep(1000);
            return number++;
        }
    }

    @Test
    public void callablethreadPool() throws ExecutionException, InterruptedException {
        Date startTime = new Date();
        System.out.println("============线程池启动时间" + startTime + "=================");
        // 线程池大小
        Integer numberThreads = 5;
        // 创建定长线程池
        ExecutorService pool = Executors.newFixedThreadPool(numberThreads);
        List<Future> actionList = new ArrayList<>();
        // 运行线程
        for (int i = 0; i < numberThreads; i++) {
            Future<Integer> out = pool.submit(new ThreadPoolCallable());
            actionList.add(out);
        }
        pool.shutdown();
        // 获取执行结果
        for (Future future : actionList) {
            System.out.println("线程执行结果:" + future.get());
        }
        Date endTime = new Date();
        System.out.println("============线程池运行时间" + (endTime.getTime() - startTime.getTime()) + "=================");
        System.out.println("============线程池结束时间" + endTime + "=================");
    }
}
