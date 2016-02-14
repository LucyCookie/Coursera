package LeetCode;

import java.util.Arrays;

/**
 * Created by qiqu on 1/28/16.
 */
public class ConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        int i = 0, j, maxL = 1;
        for (j = 1; j < nums.length; j++) {
            if (nums[j] > nums[j - 1] + 1) {
                maxL = Math.max(maxL, j - i);
                i = j;
            } else if (nums[j] == nums[j - 1]) i++;
        }
        return Math.max(maxL, j - i);
    }
}
