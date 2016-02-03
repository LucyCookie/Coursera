/**
 * Created by qiqu on 1/28/16.
 */
public class RotateArray {
    public void rotate(int[] nums, int k) {
        int size = nums.length;
        k %= size;
        if (k == 0) return;
        int[] tmp = new int[k];
        System.arraycopy(nums, size - k, tmp, 0, k);
        System.arraycopy(nums, 0, nums, k, size - k);
        System.arraycopy(tmp, 0, nums, 0, k);
    }

    //33
    /*public int search(int[] nums, int target) {
        int size = nums.length, l = 0, r = size, m, tmp;
        if (size == 1) return nums[0] == target ? 0 : -1;
        while (l != r) {
            m = (l + r) / 2;
            tmp = nums[m];
            if (tmp < target) {
                if (nums[r - 1] < tmp) l = m + 1;
                else {
                    if (nums[r - 1] >= target) l = m + 1;
                    else r = m;
                }
            } else {
                if (tmp > target) {
                    if (nums[l] > tmp) r = m;
                    else {
                        if (nums[l] <= target) r = m;
                        else l = m + 1;
                    }
                } else return m;
            }
        }
        return l < size && nums[l] == target ? 0 : -1;
    }*/

    //81
    public boolean search(int[] nums, int target) {
        int size = nums.length, l = 0, r = size, m, tmp;
        if (size == 1) return nums[0] == target;
        while (l != r) {
            m = (l + r) / 2;
            tmp = nums[m];
            if (tmp < target) {
                if (nums[r - 1] < tmp) l = m + 1;
                else {
                    if (nums[r - 1] >= target) l = m + 1;
                    else r = m;
                }
            } else {
                if (tmp > target) {
                    if (nums[l] > tmp) r = m;
                    else {
                        if (nums[l] <= target) r = m;
                        else l = m + 1;
                    }
                } else return true;
            }
        }
        return l < size && nums[l] == target;
    }
}
