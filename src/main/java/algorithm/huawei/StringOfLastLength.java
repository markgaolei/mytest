package algorithm.huawei;

import java.util.Scanner;
/**
 * @description
 * @author gaolei
 * @date 2020/11/23 9:58
 */
public class StringOfLastLength {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = "";
        while (scan.hasNext()) {
            str = scan.nextLine();
        }
        System.out.println(lengthofLast(str));
    }

    public static int lengthofLast(String str) {
        String[] s = str.split(" ");
        return s[s.length - 1].length();
    }
}
