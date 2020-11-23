package algorithm.huawei;

import java.util.Scanner;
/**
 * @description 
 * @author gaolei
 * @date 2020/11/23 10:14
 */
public class DownFromFiveHandrunds {
    public static int count=1;
    public static float distance =500f;
    public static float height =500f;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        float hightSum = 500.00f;
        System.out.println("第"+ n +"跳的路成为"+getHight(n));
    }
    public static float getHight(int n){
        if (n==1)return 500f;
        while (n>1){
            height = height/2;
            distance = distance + height*2;
            n--;
        }
        return distance;
    }
}
