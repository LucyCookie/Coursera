package LeetCode;

/**
 * Created by qiqu on 1/22/16.
 */
public class RotatedMin {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1, mid = (left + right) / 2;
        while (right != mid) {
            if (nums[left] <= nums[mid]) {
                if (nums[mid] > nums[right]) {
                    left = mid + 1;
                } else {
                    return nums[left];
                }
            } else {
                right = mid;
            }
            mid = (left + right) / 2;
        }
        return nums[mid];
    }
}
