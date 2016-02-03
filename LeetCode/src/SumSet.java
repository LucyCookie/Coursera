import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by qiqu on 1/18/16.
 */
public class SumSet {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        int size = candidates.length, index = 0;
        int[] cleaned = new int[size];
        if (size > 0) cleaned[0] = candidates[0];
        else return result;
        for (int i = 1; i < size; i++) {
            if (candidates[i] > cleaned[index]) {
                index++;
                cleaned[index] = candidates[i];
            }
        }
        result.addAll(combinationSum(cleaned, target, 0, index + 1));
        return result;
    }

    private List<List<Integer>> combinationSum(int[] cleaned, int target, int low, int high) {
        List<List<Integer>> result = new ArrayList<>();
        if (low == high - 1) {
            int tmp = cleaned[low];
            if (target % tmp == 0) {
                List<Integer> set = new ArrayList<>();
                for (int i = 0; i < target / tmp; i++) {
                    set.add(tmp);
                }
                result.add(set);
            }
            return result;
        }
        int halfT = target / 2, hRight = high, hLeft = low, mid, test;
        List<List<Integer>> tmp;
        if (target < cleaned[high - 1]) {
            int left = low, right = high, cleanBound = target - cleaned[0], tLeft = left, tRight = right;
            while (left < right - 1) {
                mid = (left + right) / 2;
                test = cleaned[mid];
                if (test <= cleanBound) {
                    left = mid;
                    tLeft = Math.max(tLeft, left);
                } else {
                    right = mid;
                    if (test > target) tRight = right;
                    else if (test < target) tLeft = mid;
                    else tLeft = tRight + 1;
                }
            }
            //now right equals the size of the cleaned cleaned
            while (tLeft < tRight - 1) {
                mid = (tLeft + tRight) / 2;
                if (cleaned[mid] < target) tLeft = mid;
                else if (cleaned[mid] > target) tRight = mid;
                else tLeft = tRight + 1;
            }
            if (tLeft == tRight + 1) {
                List<Integer> self = new ArrayList<Integer>();
                self.add(target);
                result.add(self);
            }
            while (hLeft < hRight - 1) {
                mid = (hLeft + hRight) / 2;
                test = cleaned[mid];
                if (test <= halfT) hLeft = mid;
                else hRight = mid;
            }
            tmp = combinationSum(cleaned, target, low, right);
            if (tmp != null) result.addAll(tmp);
            return result;
        }
        for (int i = hRight - 1; i >= low; i--) {
            test = cleaned[i];
            tmp = combinationSum(cleaned, target - test, low, i + 1);
            if (tmp != null) {
                for (int j = 0; j < tmp.size(); j++) {
                    tmp.get(j).add(test);
                }
                result.addAll(tmp);
            }
        }
        for (int i = hRight; i < high; i++) {
            test = cleaned[i];
            tmp = combinationSum(cleaned, target - test, low, hRight);
            if (tmp != null) {
                for (int j = 0; j < tmp.size(); j++) {
                    tmp.get(j).add(test);
                }
                result.addAll(tmp);
            }
        }
        return result;
    }
}
