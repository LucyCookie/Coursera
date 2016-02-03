/**
 * Created by qiqu on 1/27/16.
 */
public class FirstBad {
    public int firstBadVersion(int n) {
        long i=1,j=n,mid;
        while (i!=j) {
            mid=(i+j)/2;
            if (isBadVersion((int) mid)) j=mid;
            else i=mid+1;
        }
        return (int) j;
    }
    boolean isBadVersion(int version){
        return version>=1702766719;
    }
}
