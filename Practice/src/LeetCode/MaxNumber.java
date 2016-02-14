package LeetCode;

/**
 * Created by qiqu on 2/6/16.
 */
//TODO
public class MaxNumber {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] result = new int[k];
        int m = nums1.length, n = nums2.length, l1 = 0, l2 = 0, ex, max, tmp1, tmp2, o = k, pre1, pre2;
        for (int i = 0; i < o; i++) {
            ex = m + n - k - l1 - l2;
            tmp1 = tmp2 = 0;
            pre1=nums1[l1];
            pre2=nums2[l2];
            while (tmp1 <= ex && tmp2 <= ex && l1 + tmp1 < m && l2 + tmp2 < n) {
                if (nums1[l1 + tmp1] < nums2[l2 + tmp2]) {
                    pre1=Math.max(pre1, nums1[l1+tmp1]);
                    tmp1++;
                }
                else if (nums1[l1 + tmp1] > nums2[l2 + tmp2]) {
                    pre2=Math.max(pre2, nums2[l1+tmp2]);
                    tmp2++;
                }
                else {
                    if (pre1<pre2) {
                        tmp2++;
                        if (nums2[l2+tmp2]>pre2) pre2=nums2[l2+tmp2];
                    } else {

                    }
                    if (tmp1 <= tmp2) tmp2++;
                    else tmp1++;
                }
            }
            if (tmp1 <= ex && l1 + tmp1 < m) {
                max = tmp1;
                tmp1++;
                while (tmp1 <= ex && l1 + tmp1 < m) {
                    if (nums1[tmp1 + l1] > nums1[max + l1]) max = tmp1;
                    tmp1++;
                }
                result[i] = nums1[l1 + max];
                l1 += max + 1;
            } else {
                max = tmp2;
                tmp2++;
                while (tmp2 <= ex && l2 + tmp2 < n) {
                    if (nums2[tmp2 + l2] > nums2[max + l2]) max = tmp2;
                    tmp2++;
                }
                result[i] = nums2[l2 + max];
                l2 += max + 1;
            }
            k--;
        }
        return result;
    }
}
