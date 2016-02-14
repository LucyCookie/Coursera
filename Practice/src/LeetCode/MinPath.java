package LeetCode;

/**
 * Created by qiqu on 1/16/16.
 */
public class MinPath {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    result[i][j] = grid[i][j];
                } else if (i == 0) {
                    result[i][j] = grid[i][j] + result[i][j - 1];
                } else if (j == 0) {
                    result[i][j] = grid[i][j] + result[i - 1][j];
                } else
                    result[i][j] = grid[i][j] + Math.min(result[i][j - 1], result[i - 1][j]);
            }
        }
        return result[m - 1][n - 1];
    }
}
