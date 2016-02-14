package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiqu on 1/20/16.
 */
public class UglyNumber {
    //264
    public int nthUglyNumber(int n) {
        int[] primes=new int[]{2,3,5};
        return nthSuperUglyNumber(n, primes);
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        int k = primes.length, min, tmp, judge;
        int[] ugly = new int[n], travel = new int[k];
        ugly[0] = 1;
        for (int i = 1; i < n; i++) {
            min = primes[0] * ugly[travel[0]];
            judge = min;
            for (int j = 1; j < k; j++) {
                for (int l = travel[j]; l < travel[j - 1]; l++) {
                    tmp = primes[j] * ugly[l];
                    if (tmp <= ugly[i - 1]) travel[j] = l + 1;
                    else {
                        if (tmp < min) {
                            min = tmp;
                        }
                        break;
                    }

                }
            }
            if (judge == min) travel[0]++;
            ugly[i] = min;
        }
        return ugly[n - 1];
    }

    //263
    public boolean isUgly(int num) {
        while (num % 2 == 0 && num > 1) {
            num /= 2;
        }
        while (num % 3 == 0 && num > 1) {
            num /= 3;
        }
        while (num % 5 == 0 && num > 1) {
            num /= 5;
        }
        return num == 1;
    }
}
