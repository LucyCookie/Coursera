package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiqu on 1/30/16.
 */
public class Spiral {
    //54
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int n = matrix.length;
        if (n == 0) return result;
        int m = matrix[0].length;
        for (int i = 0; 2 * i < m && 2 * i < n; i++) {
            if (m - 2 * i == 1) {
                for (int j = i; j < n - i; j++) {
                    result.add(matrix[j][m - i - 1]);
                }
                break;
            }
            if (n - 2 * i == 1) {
                for (int j = i; j < m - i; j++) {
                    result.add(matrix[i][j]);
                }
                break;
            }
            for (int j = i; j < m - i - 1; j++) {
                result.add(matrix[i][j]);
            }
            for (int j = i; j < n - i - 1; j++) {
                result.add(matrix[j][m - i - 1]);
            }
            for (int j = m - i - 1; j > i; j--) {
                result.add(matrix[n - i - 1][j]);
            }
            for (int j = n - i - 1; j > i; j--) {
                result.add(matrix[j][i]);
            }
        }
        return result;
    }

    //59
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        if (n <= 0) return result;
        int k = 0;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                result[i][j] = ++k;
                result[j][n - i - 1] = k + n - 2 * i - 1;
                result[n - i - 1][n - 1 - j] = k + 2 * (n - 2 * i - 1);
                result[n - 1 - j][i] = k + 3 * (n - 2 * i - 1);
            }
            k += 3 * (n - 2 * i - 1);
        }
        if (n % 2 == 1) result[n / 2][n / 2] = (int) Math.pow(n, 2);
        return result;
    }
}
