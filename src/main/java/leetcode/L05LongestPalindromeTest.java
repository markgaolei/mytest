package leetcode;


import org.junit.Test;

/**
 * @author gaolei
 * @description 最长回文子串
 *
 * @date 2021/01/05 21:33
 */
public class L05LongestPalindromeTest {

    /**
     * 动态规划法
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        for (int l = 0; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = true;
                } else if (l == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, i + l + 1);
                }
            }
        }
        return ans;
    }

    @Test
    public void method01() {
        String s = "ababa";
        String result = longestPalindrome(s);
        System.out.println(result);
    }

}
