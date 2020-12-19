package algorithm;

import org.junit.Test;

import java.util.Scanner;

/**
 * @author gaolei
 * @description 最大不相同字符串长度
 * @date 2020/12/2 10:51
 */
public class MaxDiffLetterTest {

    /**
     * 最大相同字符串
     */
    @Test
    public void fromMaxSameLetter() {
        String str = "abcdefghklmnabcdefghklmn";
        int strLen = str.length();
        for (int i = strLen - 1; i < strLen; i--) {
            // 本次比较的固定长度，即滑动窗口长度
            int fixLength = i;
            for (int j = 0; j < strLen - i; j++) {
                // 本次比较的固定字符串
                String fixStr = str.substring(j, fixLength + j);
                for (int k = j+1; k <= strLen - i; k++) {
                    // 与固定字符串比较的
                    String compareStr = str.substring(k, k + fixLength);
                    if ((fixStr).equals(compareStr)) {
                        System.out.println("最大相同字符串是:" + fixStr + "长度是：" + fixStr.length());
                        return;
                    }
                }
            }
        }
    }
}
