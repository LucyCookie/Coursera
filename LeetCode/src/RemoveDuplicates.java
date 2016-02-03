/**
 * Created by qiqu on 1/19/16.
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        int ind = 0, size = nums.length;
        if (size == 0) return 0;
        int current = nums[0];
        for (int i = 1; i < size; i++) {
            if (nums[i] > current) {
                ind++;
                nums[ind]=nums[i];
                current = nums[i];
            }
        }
        ind++;
        return ind;
    }
}
