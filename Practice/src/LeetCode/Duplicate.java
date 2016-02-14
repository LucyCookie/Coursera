package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by qiqu on 1/22/16.
 */
public class Duplicate {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) return true;
        }
        return false;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> c = new HashSet<>();
        int size = nums.length;
        for (int i = 0; i < k + 1 && i < size; i++) {
            if (!c.add(nums[i])) return true;
        }
        for (int i = k + 1; i < size; i++) {
            c.remove(nums[i - k - 1]);
            if (!c.add(nums[i])) return true;
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        long bucket, alter;
        int size=nums.length;
        if (k == 0) return false;
        if (t < 0) return false;
        long[] c = new long[k];
        Map<Long, Long> buckets = new HashMap<>();
        for (int i = 0; i < size; i++) {
            alter = ((long)nums[i] - (long)Integer.MIN_VALUE);
            bucket = alter / ((long)t + 1l);
            if (buckets.containsKey(bucket) || (buckets.containsKey(bucket - 1) && alter - buckets.get(bucket - 1) <= t) || (buckets.containsKey(bucket + 1) && buckets.get(bucket + 1) - alter <= t))
                return true;
            if (buckets.entrySet().size() >= k) {
                long lastBucket = ((long)nums[i - k] - (long)Integer.MIN_VALUE) / ((long) t + 1l);
                buckets.remove(lastBucket);
            }
            buckets.put(bucket, alter);
        }
        return false;
    }
}
