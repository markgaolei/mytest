package leetcode;

import org.junit.Test;

import java.util.Random;

/**
 * 整数反转
 *
 * @author gaolei
 * @description 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * @date 2021/1/8 21:56
 */
public class L07ReverseIntegerTest {

    /**
     * 自己的方法
     * @param n
     * @return
     */
    public int reverse (int n) {
        int result = 0;
        int temp;
        boolean flag = n > 0 ? true : false;
        if (!flag){
            n = -n;
        }
        // 优化: 整数还是负数不用考虑，因为只要考虑x/10!=0就包含了这两种情况
        // 详细情况看method2
        while (n > 0){
            temp = n % 10;
            if (result > Integer.MAX_VALUE/10) {
                return 0;
            }
            if (result == Integer.MAX_VALUE/ 10 && temp > 7) {
                return 0;
            }
            result = result * 10 + temp;
            n = n / 10;
        }
        return result = flag ? result : -result;
    }

    public int method2(int x) {
        int rev = 0;
        int temp;
        while (x != 0) {
            temp = x % 10;
            // 边界条件判断
            if (Integer.MAX_VALUE / 10 < rev || (Integer.MAX_VALUE / 10 == rev && temp > 7)) return 0;
            if (Integer.MIN_VALUE / 10 > rev || (Integer.MIN_VALUE / 10 == rev && temp < -8)) return 0;
            rev = rev * 10 + temp;
            x /= 10;
        }
        return rev;
    }

    @Test
    public void test() {
        int x = 123;
        System.out.println(method2(x));
    }
}
