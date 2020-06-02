package algorithm.huawei;

import java.util.Arrays;
import java.util.Scanner;

public class GetN {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String stringArray = scan.next();
        int n = scan.nextInt();
        String[] array = stringArray.split(",");
        System.out.println(getNNumber(array,n));


    }
    public static  int getNNumber(String[] array, int n){
        int result=0;
        int[] arrayInt = new int[array.length];
        for (int i=0;i<array.length;i++){
            arrayInt[i] = Integer.parseInt(array[i]);
        }
        Arrays.sort(arrayInt);
        if (n>=1&&n<array.length){
            result = arrayInt[arrayInt.length-n];
        }
        return result;
    }
}
