package jvm.gc;

import org.junit.Test;

/**
 * @author gaolei
 * @description gc测试
 * @date 2021/1/23 9:26
 */
public class GcTest {

    public static void main(String[] args) {
        testGc();
    }

    public static void testGc() {
//        byte[] allocate1 = new byte[30900 * 1024];
        byte[] allocate2 = new byte[65024 * 1024];
    }
}
