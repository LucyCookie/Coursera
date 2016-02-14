package LeetCode;

/**
 * 9 easy
 * Created by Cookie on 28/7/2015.
 */
public class PalindromeNumber {
    static int getDigitNo(int i){
        int m=5, s=0, l=9;
        if (i>0){
            double d=i/Math.pow(10,m);
            while (!(d<10 && d>=1)) {
                while (d < 1) {
                    l = m;
                    m = (s + l) / 2;
                    d = i / Math.pow(10, m);
                }
                while (d >=10) {
                    s = m+1;
                    m = (s + l) / 2;
                    d = i / Math.pow(10, m);
                }
            }
        }
        return m;
    }
    static boolean isPalindrome(int x){
        if (x<10 && x>=0) return true;
        else {
            int digitNo = getDigitNo(x);
            int h, e;
            h = (int) (x / Math.pow(10, digitNo));//pow() is a super slow method
            e = x % 10;
            int i = 1;//better to have a variable memorizing the number instead of the times
            while (h == e) {
                h = (int) (x / Math.pow(10, digitNo - i)) % 10;
                e = (int) (x % Math.pow(10, i + 1) / Math.pow(10, i));
                if (i == (digitNo + 1) / 2) return true;
                i++;
            }
            return false;
        }
    }
    //a faster solution: get the reversed right half number and compare with the left half
    static boolean fastMethod(int x){
        if (x>10) {
            if (x%10!=0){
                int right=0;
                while (right<x/10) {
                    right = right * 10 + x % 10;
                    x /= 10;
                }
                if (x==right || right==x/10) return true;
            }
        } else if (x<10 && x>=0) return true;
        return false;
    }
}
