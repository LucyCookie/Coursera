package LeetCode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiqu on 1/17/16.
 * 312
 */
public class MultiplyBalloon {
    public int maxCoins(int[] nums) {
        int size = nums.length, effective = 0, i;
        int[] cleared = new int[size + 2];
        for (i = 0; i < size; i++) {
            if (nums[i] != 0) {
                effective++;
                cleared[effective] = nums[i];
            }
        }
        cleared[0] = cleared[effective + 1] = 1;
        int[][] mem = new int[effective + 2][effective + 2];
        return maxCoins(cleared, mem, 0, effective + 1);
    }

    private int maxCoins(int[] cleared, int[][] mem, int left, int right) {
        //left of left and right of right is out of range and not to be burst when trying to find the last balloon to burst in the range
        if (mem[left][right] > 0) return mem[left][right];//the range is visited;
        if (left == right - 1) return 0; //if empty range with no balloon to burst
        int i, max = 0;
        for (i = left + 1; i < right; i++) {
            max = Math.max(max, cleared[left] * cleared[i] * cleared[right] + maxCoins(cleared, mem, left, i) + maxCoins(cleared, mem, i, right));//since i is the last one to burst and left & right is there when i is burst, the sum of the range is the sum of the left of i and the right of i and the product of i*left*right
        }
        mem[left][right] = max;//mem to save out the repeated work
        return max;
    }
}
