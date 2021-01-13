package basic;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author gaolei
 * @description 测试ThreadLocalMap，GC后的引用是否会消失
 * @date 2021/1/13 9:06
 */
public class ThreadLocalDemoTest {

    @Test
    public void test1() throws InterruptedException {
        Thread t1 = new Thread(() -> test2("abc", false));
        t1.start();
        t1.join();
        System.out.println("--gc后--");
        Thread t2 = new Thread(() -> test2("test2", true));
        t2.start();
        t2.join();
    }

    /**
     * new ThreadLocal<>().set(s);
     *
     * @param s
     * @param isGc
     */
    public void test2 (String s, boolean isGc) {
        try {
            new ThreadLocal<>().set(s);
            if (isGc) {
                System.gc();
            }

            Thread t = Thread.currentThread();
            // 获取Thread类的threadLocals字段
            Class clazz = t.getClass();
            Field field = clazz.getDeclaredField("threadLocals");
            field.setAccessible(true);
            Object threadLocalMap = field.get(t);
            // 获取ThreadLocalMap对象的Table
            Class threadClazz = threadLocalMap.getClass();
            Field tableField = threadClazz.getDeclaredField("table");
            tableField.setAccessible(true);
            Object[] arr = (Object[]) tableField.get(threadLocalMap);
            for (Object o :arr) {
                if (o != null) {
                    // 获取Entry的value
                    Class entryClazz = o.getClass();
                    Field valueField = entryClazz.getDeclaredField("value");
                    valueField.setAccessible(true);
                    // 获取Entry的Key
                    Field referenceField = entryClazz.getSuperclass().getSuperclass().getDeclaredField("referent");
                    referenceField.setAccessible(true);
                    System.out.println(String.format("弱引用value: %s; 弱引用key: %s", valueField.get(o), referenceField.get(o)));
                }
            }
            // 遍历Table,检验Table的key是否全部为null
        }catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() throws InterruptedException {
        Thread t1 = new Thread(() -> test3("abc", false));
        t1.start();
        t1.join();
        System.out.println("--gc后--");
        Thread t2 = new Thread(() -> test3("test2", true));
        t2.start();
        t2.join();
    }

    /**
     * new ThreadLocal<>().set(s);
     *
     * @param s
     * @param isGc
     */
    public void test3 (String s, boolean isGc) {
        try {
            ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();
            threadLocal.set(s);
            if (isGc) {
                System.gc();
            }

            Thread t = Thread.currentThread();
            // 获取Thread类的threadLocals字段
            Class clazz = t.getClass();
            Field field = clazz.getDeclaredField("threadLocals");
            field.setAccessible(true);
            Object threadLocalMap = field.get(t);
            // 获取ThreadLocalMap对象的Table
            Class threadClazz = threadLocalMap.getClass();
            Field tableField = threadClazz.getDeclaredField("table");
            tableField.setAccessible(true);
            Object[] arr = (Object[]) tableField.get(threadLocalMap);
            for (Object o :arr) {
                if (o != null) {
                    // 获取Entry的value
                    Class entryClazz = o.getClass();
                    Field valueField = entryClazz.getDeclaredField("value");
                    valueField.setAccessible(true);
                    // 获取Entry的Key
                    Field referenceField = entryClazz.getSuperclass().getSuperclass().getDeclaredField("referent");
                    referenceField.setAccessible(true);
                    System.out.println(String.format("弱引用value: %s; 弱引用key: %s", valueField.get(o), referenceField.get(o)));
                }
            }
            // 遍历Table,检验Table的key是否全部为null
        }catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
