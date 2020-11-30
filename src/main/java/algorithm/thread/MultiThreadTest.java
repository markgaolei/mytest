package algorithm.thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author gaolei
 * @description 创建多线程3种方法
 * @date 2020/11/23 10:01
 */
public class MultiThreadTest {
    private Integer number = 10;

    /**
     * 方式1 继承Thread类,重新run方法
     */
    class ExtendMultiThread extends Thread {
        @Override
        public void run() {
            System.out.println("线程运行：" + Thread.currentThread().getName());
        }
    }

    /**
     * TODO 导入了junit依赖但是无法使用@Test注解
     * 解决：因为scope设置为test，所以只能在src目录下的test文件夹使用
     */
    @Test
    public void extendMultiThreadTest() {
        // Created threads
        for (int i = 0; i < number; i++) {
            ExtendMultiThread extendMultiThread = new ExtendMultiThread();
            extendMultiThread.start();
            System.out.print("第" + i + "个线程启动:");
        }
    }

    /**
     * 方式2：实现Runnable接口，重新run方法
     */
    class ImplementsRunableInterface implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    @Test
    public void implementsRunableInterfaceTest() {
        // Created threads
        for (int i = 0; i < number; i++) {
            ImplementsRunableInterface implementsRunableInterface = new ImplementsRunableInterface();
            new Thread(implementsRunableInterface).start();
            System.out.print("第" + i + "线程启动:");
        }
    }

    /**
     *
     */
    class ImplementsCallableInterface implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.print(Thread.currentThread().getName());
            return "线程任务执行结束";
        }
    }

    @Test
    public void implementsCallableInterfaceTest() throws InterruptedException, ExecutionException, TimeoutException {
        // Created threads
        for (int i = 0; i < number; i++) {
            Callable<String> implementsCallableInterface = new ImplementsCallableInterface();
            FutureTask<String> futureTask = new FutureTask<String>(implementsCallableInterface);
            new Thread(futureTask).start();
            System.out.print("第" + i + "线程启动:");
            String out = futureTask.get(5000, TimeUnit.MILLISECONDS);
            System.out.println("执行结果：" + out);

        }
    }

    public String runThread() {
        return "hello world";
    }
}

