package algorithm;

import java.util.Scanner;

/**
 * @author gaolei
 * @description
 * @date 2020/11/23 9:57
 */
public class CheapCatOfThrid {
    public static void main(String[] args) {
        int numhat = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入帽子的的个数");
        if (scanner.hasNextInt()) {
            numhat = scanner.nextInt();
            if (numhat < 3) {
                System.out.println(-1);
                return;
            }
        }
        int[] array = inputHatPrice(numhat);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println(array[array.length - 3]);

    }

    public static int[] inputHatPrice(int numHat) {
        int[] array = new int[numHat];
        for (int i = 0; i < numHat; i++) {
            System.out.println("请输入第" + (i + 1) + "顶帽子的价格");
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                array[i] = scanner.nextInt();
                if (array[i] <= 0 || array[i] >= 1000) {
                    System.out.println("请输入正确的0到1000正整数");
                    array[i] = scanner.nextInt();
                }
            }
        }
        return array;
    }
}
