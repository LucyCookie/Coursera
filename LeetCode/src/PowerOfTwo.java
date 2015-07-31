/**
 * Created by Cookie on 30/7/2015.
 */
public class PowerOfTwo {
    public static boolean isPowerOfTwo(int n) {
        if (n>0) {
            int v = (int) (Math.log((double) n) / Math.log((double) 2));
            double l = Math.pow(2, v);
            return (l == n);
        }
        return false;
    }
}
