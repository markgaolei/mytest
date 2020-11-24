package algorithm.thread;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description 线程
 * @author gaolei
 * @date 2020/11/23 10:03
 */
public class LamdaThreadTest {
    /**
     * 如果用普通声明变量,在多线程中使用i,不能保证原子性
     */
    private static int i = 0;

    /**
     * volatile不能做inc++操作
     */
    private volatile int volitileCount = 0;

    /**
     * 使用AtomicInteger打印线程连续
     */
    private AtomicInteger atomicInteger = new AtomicInteger();

    @Test
    public void intTypeCount() {
        runThread(i, 1000);
    }

    @Test
    public void volitileTypeCount() {
        runThread(volitileCount, 1000);
    }

    @Test
    public void atomicIntegerCount() {
        CountDownLatch  countDownLatch = new CountDownLatch(1000);
        atomicInteger.set(i);
        for (; atomicInteger.get() < 1000; atomicInteger.getAndIncrement()) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.print("这是第" + atomicInteger.get() + "线程 ");
                    System.out.print("countDownLatch的原值：" + countDownLatch.getCount());
                    countDownLatch.countDown();
                    System.out.print("countDownLatch的值是：" + countDownLatch.getCount() + " " + Thread.currentThread().getName());
                    System.out.println();
                }
            });
            try {
                if (countDownLatch.getCount() == 1) {
                    countDownLatch.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.start();
        }
    }

    private void runThread (int count, int countDownLatchNumber) {
        CountDownLatch  countDownLatch = new CountDownLatch(countDownLatchNumber);
        long startTime = System.currentTimeMillis();
        for (; i < countDownLatchNumber; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.print("这是第" + count + "线程 ");
                    System.out.print("countDownLatch的原值：" + countDownLatch.getCount());
                    countDownLatch.countDown();
                    System.out.print("countDownLatch的值是：" + countDownLatch.getCount() + " " + Thread.currentThread().getName());
                    System.out.println();
                    try {
                        if (countDownLatch.getCount() == 1) {
                            countDownLatch.await();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
        long endtime = System.currentTimeMillis();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("线程起始时间" + df.format(startTime));
        System.out.println("线程结束时间" + df.format(endtime));
    }
}
