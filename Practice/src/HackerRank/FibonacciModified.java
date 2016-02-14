package HackerRank;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by qiqu on 2/13/16.
 */
public class FibonacciModified {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] ins = s.nextLine().split(" ");
        int a = Integer.parseInt(ins[0]), b = Integer.parseInt(ins[1]), n = Integer.parseInt(ins[2]);
        BigInteger[] nums = new BigInteger[n];
        nums[0] = new BigInteger(String.valueOf(a));
        nums[1] = new BigInteger(String.valueOf(b));
        for (int i = 2; i < n; i++) {
            nums[i] = nums[i-1].multiply(nums[i-1]).add(nums[i-2]);
        }
        System.out.println(nums[n - 1].toString());
    }
}
