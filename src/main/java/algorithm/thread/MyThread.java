package algorithm.thread;
/**
 * @description
 * @author gaolei
 * @date 2020/11/23 10:04
 */
public class MyThread extends Thread {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        MyThread s = new MyThread();
        t.start();
        System.out.println("one.");
        s.start();
        System.out.println("two.");
    }

    @Override
    public void run() {
        System.out.println("Thread");
    }
}
