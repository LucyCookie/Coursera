package LeetCode;

/**
 * Created by qiqu on 1/17/16.
 */
public class ExcelDecimal {
    //168
    public String convertToTitle(int n) {
        String title = "";
        while (n > 0) {
            title = (char) ((n - 1) % 26 + 'A') + title;
            n = (n - 1) / 26;
        }
        return title;
    }

    //171
    public int titleToNumber(String s) {
        int n = s.length(), num = 0;
        for (int i = 0; i < n; i++) {
            num += (int) ((s.charAt(n - 1 - i) - 'A' + 1) * Math.pow(26, i));
        }
        return num;
    }
}
