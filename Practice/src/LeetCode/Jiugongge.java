package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by qiqu on 2/4/16.
 */
public class Jiugongge {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0) return result;
        List<String> sub = letterCombinations(digits.substring(1));
        String[] add = letterOfDigit(digits.charAt(0));
        if (sub.size() == 0) return Arrays.asList(add);
        for (String pre : sub) {
            for (String toAdd : add) {
                result.add(toAdd + pre);
            }
        }
        return result;
    }

    private String[] letterOfDigit(char digit) {
        switch (digit) {
            case '2':
                return new String[]{"a", "b", "c"};
            case '3':
                return new String[]{"d", "e", "f"};
            case '4':
                return new String[]{"g", "h", "i"};
            case '5':
                return new String[]{"j", "k", "l"};
            case '6':
                return new String[]{"m", "n", "o"};
            case '7':
                return new String[]{"p", "q", "r", "s"};
            case '8':
                return new String[]{"t", "u", "v"};
            case '9':
                return new String[]{"w", "x", "y", "z"};
        }
        return new String[]{};
    }
}
