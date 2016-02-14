package HackerRank;

import java.util.Scanner;

/**
 * Created by qiqu on 2/13/16.
 */
public class DiagonalDifference {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine()), sum1 = 0, sum2 = 0;
        String[][] nums = new String[n][n];
        for (int i = 0; i < n; i++) {
            nums[i] = s.nextLine().split(" ");
            sum1 += Integer.parseInt(nums[i][i]);
            sum2 += Integer.parseInt(nums[i][n - i - 1]);
        }
        System.out.println(Math.abs(sum1 - sum2));
    }
}
