package LeetCode;

/**
 * Created by qiqu on 1/29/16.
 */
public class Closest {
    public int threeSumClosest(int[] nums, int target) {
        int sum = 0, like;
        for (int i = 0; i < 3; i++) sum += nums[i];
        like = sum;
        for (int i = 3; i < nums.length; i++) {
            sum = sum - nums[i - 3] + nums[i];
            if (Math.abs(sum - target) < Math.abs(like - target)) like = sum;
        }
        return like;
    }
}
