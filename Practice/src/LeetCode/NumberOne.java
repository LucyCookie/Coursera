package LeetCode;

/**
 * Created by qiqu on 1/26/16.
 */
public class NumberOne {
    public int countDigitOne(int n) {
        long sum = 0, pre, post;
        for (long i = 1; i <= n; i *= 10) {
            pre = n / i / 10;
            post = n % (10 * i);
            if (post / i > 1) sum += pre * i + i;
            else if (post / i == 1) sum += i == 1 ? pre + 1 : pre * i + post - i + 1;
            else sum += pre * i;
        }
        return (int) sum;
    }
}
