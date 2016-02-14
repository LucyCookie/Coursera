package LeetCode;

/**
 * Created by qiqu on 1/26/16.
 */
public class PowerOfThree {
    public boolean isPowerOfThree(int n) {
        int rem=0;
        while (rem==0 && n>1) {
            rem=n%3;
            n/=3;
        }
        return rem==0 && n==1;
    }
}
