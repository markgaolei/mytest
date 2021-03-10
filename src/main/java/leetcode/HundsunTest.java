package leetcode;

import java.util.*;

public class HundsunTest {

    /**
     * 第一题：
     * 根据第一行输入的数字和第二行输入的字符串,将字符串按空格分割，求每个数字的每个百分位的和
     * eg:
     * 输入：第一行 4 第二行 123 34 67 89
     * 输出： 6 7 13 17
     *
     * 第二题：
     * 输入：字符串【A-Z,a-z,0-9】，count每个相同字符串最小间隔
     * 输出：count数量时每个相同字符串最小间隔
     * eg:
     * 输入：ddhhgg && count=2
     * 输出：dghdgh
     */
    public static void main(String[] args) {

        // 第一题：解决方法
        getInput ();

        // 第二题：解决方法
        getInput2();

    }

    public static void getInput () {
        // 获取第一行数据:种类
        Scanner s = new Scanner(System.in);
        int number = Integer.valueOf(s.nextLine());

        // 获取第二行数据
        String str = s.nextLine();

        // 获取对应产品数
        getAll(number, str);
    }

    public static void getAll(int number, String s) {
        String[] array = s.split(" ");
        for (int i = 0; i < array.length; i++) {
            int result = 0;
            int n = Integer.valueOf(array[i]);
            while (n > 0) {
                result += n%10;
                n = n/10;
            }
            System.out.print(result + " ");
        }
    }

    public static void getInput2() {
        Scanner s = new Scanner(System.in);
        int count = Integer.valueOf(s.nextLine());
        String str = s.nextLine();
        getRank(count, str);
    }

    public static void getRank(int count, String str) {
        char[] charArray = str.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : charArray) {
            if (map.keySet().contains(c)) {
                Integer a = map.get(c);
                a++;
            } else {
                map.put(c, 1);
            }
        }
        // 取出Map中对应value最长的，验证最小长度为 最小间隔*(最多字符的长度 - 1) + 最多字符的长度
        // 即表达式：字符长度 > count * (number - 1) + number
        int maxLength = 0;
        for (char c1 : map.keySet()) {
            Integer c1Length = map.get(c1);
            if (c1Length > maxLength) {
                maxLength = c1;
            }
        }

        if (charArray.length > (count * (maxLength - 1) + maxLength) ) {
            // 按自然排序排列

            // 输出结果
        }else {
            System.out.println(-1);
        }
    }

}
