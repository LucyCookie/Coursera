package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cookie on 30/7/2015.
 */
public class Range {
    public static List<String> summaryRanges(int[] nums) {
        List<String> ranges=new ArrayList<>();
        if (nums.length>0) {
            int h, t, i;
            h = nums[0];
            for (i = 1; i < nums.length; i++) {
                if (nums[i] > nums[i - 1] + 1) {
                    t = nums[i - 1];
                    if (t > h) {
                        ranges.add(Integer.toString(h) + "->" + Integer.toString(t));
                    } else ranges.add(Integer.toString(t));
                    h = nums[i];
                }
            }
            t = nums[i - 1];
            if (t > h) {
                ranges.add(Integer.toString(h) + "->" + Integer.toString(t));
            } else ranges.add(Integer.toString(t));
        }
        return ranges;
    }
}
