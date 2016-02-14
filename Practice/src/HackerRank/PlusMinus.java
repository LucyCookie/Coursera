package HackerRank;

import java.util.Scanner;

/**
 * Created by qiqu on 2/13/16.
 */
public class PlusMinus {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine()), pos = 0, neg = 0, zeros=0;
        String[] nums = s.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            if (Integer.parseInt(nums[i])>0) pos++;
            else if (Integer.parseInt(nums[i])<0) neg++;
            else zeros++;
        }
        System.out.println(1.0d * pos / n);
        System.out.println(1.0d * neg / n);
        System.out.println(1.0d * zeros / n);
    }
}
