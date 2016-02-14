package LeetCode;

/**
 * Created by qiqu on 1/30/16.
 */
public class SurroundedRegions {
    public void solve(char[][] board) {
        int n = board.length;
        if (n == 0) return;
        int m = board[0].length;
        int[][] explore = new int[n][m];
        for (int i = 0; i < (n + 1) / 2 && i < (m + 1) / 2; i++) {
            if (m - 2 * i == 1) {
                for (int j = i; j < n - i; j++) explore(board, explore, j, m - i - 1);
                for (int j = i; j < n - i; j++) explore(board, explore, n - j - 1, m - i - 1);
                break;
            }
            if (n - 2 * i == 1) {
                for (int j = i; j < m - i; j++) explore(board, explore, i, j);
                for (int j = i; j < m - i; j++) explore(board, explore, i, m - j - 1);
                break;
            }
            for (int j = i; j < m - i - 1; j++) explore(board, explore, i, j);
            for (int j = i; j < n - i - 1; j++) explore(board, explore, j, m - i - 1);
            for (int j = m - i - 1; j > i; j--) explore(board, explore, n - i - 1, j);
            for (int j = n - i - 1; j > i; j--) explore(board, explore, j, i);
        }
        for (int i = 0; i < (n + 1) / 2 && i < (m + 1) / 2; i++) {
            if (m - 2 * i == 1) {
                for (int j = i; j < n - i; j++) explore(board, explore, j, m - i - 1);
                for (int j = i; j < n - i; j++) explore(board, explore, n - j - 1, m - i - 1);
                break;
            }
            if (n - 2 * i == 1) {
                for (int j = i; j < m - i; j++) explore(board, explore, i, j);
                for (int j = i; j < m - i; j++) explore(board, explore, i, m - j - 1);
                break;
            }
            for (int j = n - i - 1; j > i; j--) explore(board, explore, n - j, i);
            for (int j = m - i - 1; j > i; j--) explore(board, explore, n - i - 1, m - j);
            for (int j = i; j < n - i - 1; j++) explore(board, explore, n - j - 2, m - i - 1);
            for (int j = i; j < m - i - 1; j++) explore(board, explore, i, m - j - 2);
        }
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) if (explore[i][j] != 1 && board[i][j] == 'O') board[i][j] = 'X';
    }

    private void explore(char[][] board, int[][] explore, int i, int j) {
        if (board[i][j] == 'O' && (i == 0 || j == 0 || i == board.length - 1 || j == board[0].length - 1 || explore[i - 1][j] == 1 || explore[i + 1][j] == 1 || explore[i][j - 1] == 1 || explore[i][j + 1] == 1))
            explore[i][j] = 1;
    }
}
