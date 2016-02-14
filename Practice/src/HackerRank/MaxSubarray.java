package HackerRank;

import java.util.Scanner;

/**
 * Created by qiqu on 2/13/16.
 */
public class MaxSubarray {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int T= Integer.parseInt(s.nextLine()), sum=0;
        for (int i = 0; i < T; i++) {
            int n=Integer.parseInt(s.nextLine());
            String[] nums=s.nextLine().split(" ");
            int max= Integer.parseInt(nums[0]), maxC=max, gap=0, pre=maxC, cur=maxC;
            for (int j = 1; j < n; j++) {
                int tmp= Integer.parseInt(nums[j]);
                if (tmp>=0) {
                    if (max<0) {
                        pre=cur=maxC=tmp;
                        max=tmp;
                    } else {
                        max+=tmp;
                        if (gap+tmp>0) {
                            if (tmp>cur) {
                                pre=cur;
                                cur=tmp;
                                gap=0;
                            } else {
                                
                            }
                        }
                    }
                } else {

                }
            }
        }
        System.out.println(sum);
    }
}
