package algorithm;

import org.junit.Test;

import java.util.Scanner;

/**
 * @author gaolei
 * @description 最大不相同字符串长度
 * @date 2020/12/2 10:51
 */
public class MaxDiffLetterTest {

    @Test
    public void fromMax2Min() {
        String str = "qwerqwer";
        int strLen = str.length();
        for (int i = strLen - 1; i < strLen; i--) {
            int fixLength = i;
            for (int j = 0; j < strLen - i; j++) {
                String fixStr = str.substring(j, fixLength + j);
                for (int k = 1; k <= strLen - i; k++) {
                    String compareStr = str.substring(k, fixLength + k);
                    if ((fixStr).equals(compareStr)) {
                        System.out.println("最大相同字符串是:" + fixStr + "长度是：" + fixStr.length());
                    }
                }
            }
        }
    }
}
