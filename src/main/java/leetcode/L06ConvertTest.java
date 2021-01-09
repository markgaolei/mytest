package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaolei
 * @description Z字形变换
 *
 * @date 2021/01/06 21:33
 */
public class L06ConvertTest {

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        // 1.获取Z字形图案的非空行
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            // 2.当是第一行或者最后一行时，转换方向 如果是第一行则让curRow+1 如果是最后一行 curRow-1
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }

    @Test
    public void method1() {
        String s = "aaaaaaaabbbbbbsdf";
        System.out.println("test" + convert(s, 3));
    }

}
