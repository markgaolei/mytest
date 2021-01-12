package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 整数反转
 *
 * @author gaolei
 * @description 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * @date 2021/1/8 21:56
 */
public class L08AtoiTest {

    /**
     * 自写方法还有问题
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        int rev = 0;
        // s带有符号+/-需要转为有符号整数
        char[] chars = s.trim().toCharArray();
        boolean isPositiveNum = true;
        int temp = 0;
        for (int i=0; i < chars.length; i++) {
            // 整数部分后有无效字符串等则忽略后面的字符
            if (chars[i] != '0' && chars[i] != '1' && chars[i] != '2' && chars[i] != '3' && chars[i] != '4' && chars[i] != '5' && chars[i] != '6' && chars[i] != '7' && chars[i] != '8' && chars[i] != '9' && chars[i] != '-' && chars[i] != '+') {
                return rev;
            }
            // 处理符号
            // bug1:i == 0 && chars[0] == '-' || chars[0] == '+' 只要为char[0]是+ 就会为true
            // bug2:未考虑多个符号情况，如："-+42"
            if (i == 0 && (chars[i] == '-' || chars[i] == '+')) {
                isPositiveNum = chars[i] == '-' ? false : true;
                continue;
            }else if (i != 0 && (chars[i] == '-' || chars[i] == '+')){
                return 0;
            }
            // 如果为负数，则处理第一位
            temp = Integer.parseInt(Character.toString(chars[i]));
            if (i != 0 && !isPositiveNum) {
                temp *= -1;
            }
            // s超过[−2^31,  2^31 − 1]则最大/最小整数
            if (isPositiveNum &&  (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE/10 && temp > 7))) return Integer.MAX_VALUE;
            if (!isPositiveNum &&  (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE/10 && temp > 8))) return Integer.MIN_VALUE;
            rev = rev * 10 + temp;
        }
        return rev;
    }

    private int method2 (String str) {
        str = str.trim();
        if (str.length() == 0) return 0;
        if (!Character.isDigit(str.charAt(0))
                && str.charAt(0) != '-' && str.charAt(0) != '+')
            return 0;
        int ans = 0;
        boolean neg = str.charAt(0) == '-';
        int i = !Character.isDigit(str.charAt(0)) ? 1 : 0;
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            int tmp = ((neg ? Integer.MIN_VALUE : Integer.MIN_VALUE + 1) + (str.charAt(i) - '0')) / 10;
            if (tmp > ans) {
                return neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = ans * 10 - (str.charAt(i++) - '0');
        }
        return neg ? ans : -ans;
    }

    @Test
    public void test1() {
        String s = " +-42";
        System.out.println(myAtoi(s));
        System.out.println(automaton(s));
        System.out.println(method2(s));
    }

    /**
     * 自动机方式 确定有限状态机（deterministic finite automaton, DFA
     *
     * @param str
     * @return
     */
    private int automaton (String str) {
        Automaton automaton = new Automaton();
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            automaton.getAns(str.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);
    }

    class Automaton {
        public String state = "start";
        public long ans = 0;
        public int sign = 1;
        Map<String, String[]> map = new HashMap<>();

        private Automaton() {
            // 构建状态表
            //        状态                        ‘ ’     +/-       number       other
            map.put("start",     new String[] {"start", "signed", "in_number", "end"});
            map.put("signed",    new String[] {"end", "end", "in_number", "end"});
            map.put("in_number", new String[] {"end", "end", "in_number", "end"});
            map.put("end",       new String[] {"end", "end", "end", "end"});
        }

        public long getAns(char c) {
            state = map.get(state)[getCol(c)];
            if (Objects.equals("in_number", state)) {
                ans = ans * 10 + c - '0';
                ans = sign == 1 ? Math.min(ans, Integer.MAX_VALUE) : Math.min(ans, -(long)Integer.MIN_VALUE);
            } else if (Objects.equals("signed", state)) {
                sign = c == '+' ? 1 : -1;
            }
            return ans;
        }

        private int getCol(char c) {
            if (c == ' ') {
                return 0;
            }
            if (c == '+' || c == '-') {
                return 1;
            }
            if (Character.isDigit(c)) {
                return 2;
            }
            return 3;
        }
    }

}
