import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiqu on 1/19/16.
 */
public class LongSub {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int right = -1, max = 0, left = 0;
        for (char c : s.toCharArray()) {
            right++;
            if (map.get(c) != null) left = Math.max(map.get(c) + 1, left);
            max = Math.max(max, right - left + 1);
            map.put(c, right);
        }
        max = Math.max(max, right - left + 1);
        return max;
    }
}
