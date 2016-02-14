package LeetCode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by qiqu on 2/4/16.
 */
public class Game {
    public void gameOfLife(int[][] board) {
        List<int[]> change = new LinkedList<>();
        int n = board.length;
        if (n < 1) return;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                test(board, change, i, j, n, m);
            }
        }
        for (int[] toChange : change) {
            board[toChange[0]][toChange[1]] = 1 - board[toChange[0]][toChange[1]];
        }
    }

    private void test(int[][] board, List<int[]> change, int i, int j, int n, int m) {
        int count = 0;
        if (i > 0) count += board[i - 1][j];
        if (j > 0) count += board[i][j - 1];
        if (i < n - 1) count += board[i + 1][j];
        if (j < m - 1) count += board[i][j + 1];
        if (i > 0 && j > 0) count += board[i - 1][j - 1];
        if (i > 0 && j < m - 1) count += board[i - 1][j + 1];
        if (i < n - 1 && j < m - 1) count += board[i + 1][j + 1];
        if (i < n - 1 && j > 0) count += board[i + 1][j - 1];
        if (count < 2 || count > 3) {
            if (board[i][j] == 1) change.add(new int[]{i, j});
        } else if (board[i][j] != 1 && count == 3) change.add(new int[]{i, j});
    }
}
