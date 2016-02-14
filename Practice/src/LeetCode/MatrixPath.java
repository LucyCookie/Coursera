package LeetCode;

/**
 * Created by qiqu on 1/27/16.
 */
public class MatrixPath {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length, max = 0;
        if (n == 0) return 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, longestIncreasingPath(matrix, i, j, dp));
            }
        }
        return max;
    }

    private int longestIncreasingPath(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] > 0) return dp[i][j];
        int left, right, up, down, self = matrix[i][j];
        left = i > 0 && matrix[i - 1][j] > self ? longestIncreasingPath(matrix, i - 1, j, dp) : 0;
        right = i < matrix.length - 1 && matrix[i + 1][j] > self ? longestIncreasingPath(matrix, i + 1, j, dp) : 0;
        up = j > 0 && matrix[i][j - 1] > self ? longestIncreasingPath(matrix, i, j - 1, dp) : 0;
        down = j < matrix[0].length - 1 && matrix[i][j + 1] > self ? longestIncreasingPath(matrix, i, j + 1, dp) : 0;
        return dp[i][j] = Math.max(Math.max(left, right), Math.max(up, down)) + 1;
    }
}
