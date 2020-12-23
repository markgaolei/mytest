package leetcode;

import org.junit.Test;

/**
 * @author gaolei
 * @description 顺时针打印数据
 * @date 2020/12/22 13:20
 */
public class PrintCircleWithTimeTest {

    @Test
    public void printCircleWithTimeRun() {
        int[][] array = new int[4][4];
        array[3] = new int[]{12, 13, 14, 15};
        array[2] = new int[]{9, 10, 11, 12};
        array[1] = new int[]{5, 6, 7, 8};
        array[0] = new int[]{1, 2, 3, 4};
        // 预期输出结果1,2,3,4,8,12,15,14,13,12,9,5,6,7,11,10
        printCircleWithTime(array);
    }

    private void printCircleWithTime(int [][] array) {
        int length = array.length;
        int circleNumber = length/2; //需要打印几个回
        int circleLength = 0; //每次打印边长
        for (int i = 0; i < circleNumber; i++) {
            // 第一次打印最顶层边，从第二个元素开始打印，打印length-2
            circleLength = length - (i * 2);
            for (int j = 0; j < circleLength - 1; j++) {
                System.out.println(array[length - 1 - i][j+i]);
            }
            // 第二次打印右边
            for (int j = circleLength - 1; j >= 0; j--) {
                System.out.println(array[length - 1 -j][j]);
            }
            // 第三次打印下边
            for (int j = 0; j < circleLength - 2; j++) {
                System.out.println(array[i][length- 1 - i - j - 1]);
            }
            // 第四次打印左边
            for (int j = 0; j < circleLength - 1; j++) {
                System.out.println(array[i][j+i+1]);
            }
        }
    }
}
