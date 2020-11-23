package algorithm.thread;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
/**
 * @description
 * @author gaolei
 * @date 2020/11/23 10:03
 */
public class LamdaThread {
    private static int i = 0;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(100000);
        long startTime = System.currentTimeMillis();
        for (i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    System.out.println("这是第" + i + "线程");
                }
            });
            thread.start();
            countDownLatch.countDown();
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long endtime = System.currentTimeMillis();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("线程起始时间" + df.format(startTime));
        System.out.println("线程结束时间" + df.format(endtime));

    }
}
