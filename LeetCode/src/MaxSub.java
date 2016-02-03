/**
 * Created by qiqu on 1/21/16.
 */
public class MaxSub {
    public int maxSubArray(int[] nums) {
        int now = 0, bar = 0, max = nums[0], size = nums.length, x;
        for (int i = 1; i < size; i++) {
            x = nums[i];
            now += x;
            if (now > max) {
                max = now + Math.max(0, max + bar);
                bar = 0;
                now = 0;
            } else if (now < 0) {
                bar += now;
                now = 0;
            } else {
                if (now + bar > 0) {
                    max = now + Math.max(0, max + bar);
                    bar = 0;
                    now = 0;
                }
            }
        }
        return max;
    }
}
