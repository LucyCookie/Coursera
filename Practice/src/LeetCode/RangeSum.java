package LeetCode;

import java.util.*;

/**
 * Created by qiqu on 1/27/16.
 */
public class RangeSum {
    //327
    public int countRangeSum(int[] nums, int lower, int upper) {
        int size = nums.length, num = 0;
        long[] sum = new long[size];
        for (int i = 0; i < size; i++) {
            num += rangeSum(nums, lower, upper, i, sum);
        }
        return num;
    }

    private int rangeSum(int[] nums, int lower, int upper, int i, long[] sum) {
        sum[i] = nums[i];
        int num = 0;
        if ((sum[i] - lower + 1) * (sum[i] - upper - 1) < 0) num++;
        for (int j = 0; j < i; j++) {
            sum[j] += sum[i];
            if ((sum[j] - lower + 1) * (sum[j] - upper - 1) < 0) num++;
        }
        return num;
    }

    //315
    public List<Integer> countSmaller(int[] nums) {
        int size = nums.length;
        if (size == 0) return new ArrayList<>();
        Integer[] count = new Integer[size];
        count[size - 1] = 0;
        BSTNode root = new BSTNode(nums[size - 1]);
        for (int i = size - 2; i >= 0; i--) {
            count[i] = insertNode(nums, i, root);
        }
        return Arrays.asList(count);
    }

    private int insertNode(int[] nums, int i, BSTNode root) {
        int num = nums[i], count = 0;
        BSTNode node = new BSTNode(num), point = root;
        while (true) {
            if (num == point.val) {
                point.same++;
                return count + point.smaller;
            } else if (num < point.val) {
                point.smaller++;
                if (point.left != null) point = point.left;
                else {
                    point.left = node;
                    return count;
                }
            } else {
                count += point.smaller + point.same;
                if (point.right != null) point = point.right;
                else {
                    point.right = node;
                    return count;
                }
            }
        }
    }

    private class BSTNode {
        int val, smaller, same;
        BSTNode left, right;

        BSTNode(int v) {
            val = v;
            same = 1;
            smaller = 0;
        }
    }
}
