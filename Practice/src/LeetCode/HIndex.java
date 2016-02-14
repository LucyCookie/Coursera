package LeetCode;

import java.util.Arrays;

/**
 * Created by qiqu on 1/24/16.
 */
public class HIndex {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int size = citations.length, i;
        for (i = 0; i < size; i++) {
            if (citations[size - 1 - i] < 1 + i) return i;
        }
        return i;
    }
}
