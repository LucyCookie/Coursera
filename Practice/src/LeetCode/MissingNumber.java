package LeetCode;

/**
 * Created by qiqu on 1/18/16.
 * LeetCode 268
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int n=nums.length;
        long exp=n*(n+1)/2, sum=0;
        for (int i=0;i<n;i++){
            sum+=nums[i];
        }
        return (int) (exp-sum);
    }
}
