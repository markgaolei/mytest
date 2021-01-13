package basic;

import org.junit.Test;

/**
 * @author gaolei
 * @description 使用ThreadLocal的时候，在异步场景下是无法给子线程共享父线程中创建的线程副本数据的
 * 
 * @date 2021/1/13 10:12
 */
public class InheritableThreadLocalDemoTest {
    @Test
    public void test () {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        threadLocal.set("父线程数据threadLocal");
        inheritableThreadLocal.set("父线程数据inheritableThreadLocal");
        new Thread(() -> System.out.println("threadLocal获取父线程数据：" + threadLocal.get())).start();
        new Thread(() -> System.out.println("inheritableThreadLocal获取父线程数据：" + inheritableThreadLocal.get())).start();
    }
}
