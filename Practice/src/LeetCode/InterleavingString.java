package LeetCode;

import java.util.Arrays;

/**
 * Created by qiqu on 1/24/16.
 */
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n1 = s1.length(), n2 = s2.length(), n3 = s3.length(), i = 0, j = 0, k = 0;
        if (n1 + n2 != n3) return false;
        int[][] possible = new int[n1 + 1][n2 + 1];
        for (int[] p : possible) Arrays.fill(p, -1);
        possible[0][0] = 1;
        return isPossible(s1, s2, s3, 0, 0, possible) == 1 ? true : false;
    }

    private int isPossible(String s1, String s2, String s3, int x, int y, int[][] possible) {
        if (x >= s1.length() && y >= s2.length()) return 1;
        int result = 1;
        if (x < s1.length()) {
            if (possible[x + 1][y] == -1) {
                if (s1.charAt(x) == s3.charAt(x + y)) {
                    possible[x + 1][y] = isPossible(s1, s2, s3, x + 1, y, possible);
                } else possible[x + 1][y] = 0;
            }
            result *= (1 - possible[x + 1][y]);
        }
        if (y < s2.length()) {
            if (possible[x][y + 1] == -1) {
                if (s2.charAt(y) == s3.charAt(x + y)) {
                    possible[x][y + 1] = isPossible(s1, s2, s3, x, y + 1, possible);
                } else possible[x][y + 1] = 0;
            }
            result *= (1 - possible[x][y + 1]);
        }
        return 1 - result;
    }
}
