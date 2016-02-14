package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiqu on 1/17/16.
 */
public class RecurringDecimal {
    public String fractionToDecimal(int x, int y) {
        long numerator, denominator;
        String end = "";
        if (1l * x * y < 0) end += "-";
        numerator = Math.abs((long) x);
        denominator = Math.abs((long) y);
        long quotient = (numerator / denominator), r = (numerator % denominator);
        if (r == 0) return end + String.valueOf(quotient);
        Map<Long, Integer> rems = new HashMap<>();
        String deci = "";
        while (r != 0) {
            if (rems.containsKey(r)) {
                int start = rems.get(r);
                return end + quotient + "." + deci.substring(0, start) + "(" + deci.substring(start) + ")";
            }
            rems.put(r, deci.length());
            deci += 10 * r / denominator;
            r = 10 * r % denominator;
        }
        return end + quotient + "." + deci;
    }
}
