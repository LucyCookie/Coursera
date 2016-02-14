package LeetCode;

import java.util.*;

/**
 * Created by qiqu on 2/6/16.
 */
public class Majority {
    //169
    /*public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int size=nums.length;
        return nums[size/2];
    }*/

    //229
    public List<Integer> majorityElement(int[] nums) {
        int size = nums.length, split = (size - 1) / 3, target = size / 3;
        List<Integer> result = new ArrayList<>();
        if (size < 3) {
            for (int num : nums) {
                if (!result.contains(num)) result.add(num);
            }
            return result;
        }
        Arrays.sort(nums);
        if (nums[split] == nums[size - 1 - split]) result.add(nums[split]);
        else {
            check(nums, 0, size - 2 - split, split, split, target, result);
            check(nums, split + 1, size - 1, size - 1 - split, size - 1 - split, target, result);
        }
        return result;
    }

    private void check(int[] nums, int l, int r, int cl, int cr, int target, List<Integer> result) {
        if (l == cl && r == cr) return;
        if (l < cl) {
            int c = (l + cl) / 2;
            if (nums[c] == nums[cl]) cl = c;
            else l = c + 1;
        }
        if (r > cr) {
            int c = (r + cr + 1) / 2;
            if (nums[c] == nums[cr]) cr = c;
            else r = c - 1;
        }
        if (cr - cl + 1 > target) result.add(nums[cl]);
        else check(nums, l, r, cl, cr, target, result);
    }
}
