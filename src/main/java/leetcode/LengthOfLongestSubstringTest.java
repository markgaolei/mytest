package leetcode;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author gaolei
 * @description 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * @date 2020/12/23 13:35
 */
public class LengthOfLongestSubstringTest {

    @Test
    public void testRun() throws InterruptedException {
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }

    /**
     * 滑动窗口思想解决最大无重复子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) throws InterruptedException {
        if (s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            // 如果子串不符合预期要求则舍弃最左边的，例如:abda则舍弃左边的a
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            // 计算子串最大长度
            max = Math.max(max, i - left + 1);
        }

        while (true) {
            Thread.sleep(1000);
            System.out.println("睡1秒");
        }
    }
}
