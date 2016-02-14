package HackerRank;

import java.util.Scanner;

/**
 * Created by qiqu on 2/13/16.
 */
public class ArraySum {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int n= Integer.parseInt(s.nextLine()), sum=0;
        String[] nums=s.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            sum+=Integer.parseInt(nums[i]);
        }
        System.out.println(sum);
    }
}
