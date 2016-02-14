package LeetCode;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiqu on 2/4/16.
 */
public class Permutation {
    //31
    public void nextPermutation(int[] nums) {
        nextPermutation(nums, 0);
    }

    private boolean nextPermutation(int[] nums, int i) {
        int size = nums.length;
        if (i > size - 2) return false;
        boolean found = nextPermutation(nums, i + 1);
        if (found) return true;
        int target = nums[i];
        for (int j = i + 1; j < size; j++) {
            if (nums[j] > target) {
                nums[i] = nums[j];
                nums[j] = target;
                return true;
            }
        }
        System.arraycopy(nums, i + 1, nums, i, size - i - 1);
        nums[size - 1] = target;
        return false;
    }

    //46
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();
        return permute(nums, 0);
    }

    private List<List<Integer>> permute(int[] nums, int i) {
        int size = nums.length, target;
        List<List<Integer>> result = new ArrayList<>(), pre;
        List<Integer> clone;
        if (i == size - 1) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(nums[i]);
            result.add(tmp);
            return result;
        }
        pre = permute(nums, i + 1);
        target = nums[i];
        for (List<Integer> c : pre) {
            for (int j = 0; j < c.size(); j++) {
                clone = cloneList(c);
                clone.add(0, c.get(j));
                clone.remove(j + 1);
                clone.add(j + 1, target);
                result.add(clone);
            }
            c.add(0, target);
            result.add(c);
        }
        return result;
    }

    private List<Integer> cloneList(List<Integer> c) {
        List<Integer> clone = new ArrayList<>();
        for (Integer n : c) {
            clone.add(n);
        }
        return clone;
    }

    //60
    public String getPermutation(int n, int k) {
        List<Integer> ori = new ArrayList<>();
        for (int i = 1; i <= n; i++) ori.add(i);
        long t = factorial(n - 1);
        String result = "";
        int digit, i = 1;
        while (ori.size() > 1) {
            digit = (int) ((k - 1) / t);
            result += ori.get(digit);
            ori.remove(digit);
            k = (int) ((k - 1) % t + 1);
            t /= n - i;
            i++;
        }
        result += ori.get(0);
        return result;
    }

    private long factorial(int n) {
        long t = 1;
        for (long i = 1; i <= n; i++) t *= i;
        return t;
    }
}
