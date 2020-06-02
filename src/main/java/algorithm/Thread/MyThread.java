package algorithm.Thread;

public class MyThread extends Thread {
    public static void main(String[] args) {
        MyThread t=new MyThread();
        MyThread s=new MyThread();
        t.start();
        System.out.println("one.");
        s.start();
        System.out.println("two.");
    }
    public void run() {
        System.out.println("Thread");
    }
}
