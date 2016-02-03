import java.util.Arrays;

/**
 * Created by qiqu on 1/24/16.
 */
public class WiggleSort {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int size = nums.length;
        int[] sorted = new int[size];
        for (int i = 0; i < (size + 1) / 2; i++) {
            sorted[2 * i] = nums[(size - 1) / 2 - i];
            if (2 * i + 1 < size) sorted[2 * i + 1] = nums[size - i - 1];
        }
        System.arraycopy(sorted, 0, nums, 0, size);
    }
}
