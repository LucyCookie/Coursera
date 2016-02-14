package LeetCode;

/**
 * Created by qiqu on 1/28/16.
 */
public class PalindromeString {
    public boolean isPalindrome(String s) {
        char[] c = s.toLowerCase().toCharArray();
        String[] t=s.split("\\s++");
        int size = c.length, i = 0, j = size - 1;
        while (i < j) {
            while (i <= j && (c[i] - 'a') * (c[i] - 'z') > 0 && (c[i] - '0') * (c[i] - '9') > 0) i++;
            while (j >= i && (c[j] - 'a') * (c[j] - 'z') > 0 && (c[j] - '0') * (c[j] - '9') > 0) j--;
            if (i < j && c[i] != c[j]) return false;
            i++;
            j--;
        }
        return true;
    }
}
