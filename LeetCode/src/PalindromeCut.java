/**
 * Created by qiqu on 1/17/16.
 * 132
 */
public class PalindromeCut {
    public int minCut(String s) {
        int n = s.length(), i, j;
        int[] cut = new int[n + 1];
        for (i = 0; i <= n; i++) cut[i] = i - 1;
        char[] letters = s.toCharArray();
        for (i = 0; i < n; i++) {
            for (j = 0; j <= i && i + j < n && letters[i - j] == letters[i + j]; j++) {
                cut[i + j + 1] = Math.min(cut[i - j] + 1, cut[i + j + 1]);
            }
            for (j = 0; j <= i && i + 1 + j < n && letters[i - j] == letters[i + 1 + j]; j++) {
                cut[i + j + 2] = Math.min(cut[i - j] + 1, cut[i + j + 2]);
            }
        }
        return cut[n];
    }

    private boolean isPalindrome(String s) {
        String r = s.substring((s.length() + 1) / 2, s.length());
        for (int i = 0; i < r.length(); i++) {
            if (r.charAt(r.length() - 1 - i) != s.charAt(i)) return false;
        }
        return true;
    }
}
