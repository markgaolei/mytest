package algorithm.huawei;

import java.util.Scanner;
/**
 * @description 
 * @author gaolei
 * @date 2020/11/23 10:07
 */
public class ContainsSpcialChar {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = "";
        char c = ' ';
        int count = 0;
        System.out.print("请输入字符串");
        str = scan.nextLine().toLowerCase();
        System.out.print("请输入字符");
        c = scan.next().charAt(0);
        for (int i=0; i<str.length(); i++){
            char[] chars = str.toCharArray();
            if (chars[i]==c){
                count++;
            }
        }
        System.out.println(str + "包含" + count + "个" + c);
    }
}
