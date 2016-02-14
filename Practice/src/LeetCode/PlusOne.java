package LeetCode;

/**
 * Created by qiqu on 1/21/16.
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int last = digits.length - 1;
        return plusOne(digits, last);
    }

    private int[] plusOne(int[] digits, int last) {
        int add = digits[last] + 1;
        if (add < 10) {
            digits[last] = add;
            return digits;
        } else {
            if (last == 0) {
                int size = digits.length;
                int[] upped = new int[size + 1];
                System.arraycopy(digits, 0, upped, 1, size);
                upped[0] = 1;
                upped[1] = 0;
                return upped;
            } else {
                digits[last] = 0;
                return plusOne(digits, --last);
            }
        }
    }
}
