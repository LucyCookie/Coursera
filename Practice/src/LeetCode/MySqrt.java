package LeetCode;

/**
 * Created by qiqu on 1/15/16.
 */
public class MySqrt {
    public int mySqrt(int x) {
        int digit=0, tmp=x;
        tmp/=10;
        while (tmp>=1){
            digit++;
            tmp/=10;
        }
        int base= (int) Math.pow(10,digit/2);
        int mid=x/base, min = mid/10, max = 2*mid;
        while (min!=max){
            long sqr=1l*mid*mid;
            if (sqr==x || mid==min) return mid;
            if (sqr>x) {
                max=mid;
            } else {
                min=mid;
            }
            mid = (min + max) / 2;
        }
        return min;
    }
}
