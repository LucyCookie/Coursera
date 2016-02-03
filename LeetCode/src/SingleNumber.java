import java.util.Arrays;

/**
 * Created by qiqu on 1/15/16.
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int c=0;
        for (int i:nums){
            c^=i;
        }
        return c;
    }
}
