/**
 * Created by qiqu on 1/23/16.
 * 121-123
 */
public class MaxProfit {
    /*public int maxProfit(int[] prices) {
        int b = Integer.MAX_VALUE, s = b, now = 0, max = 0;
        for (int price : prices) {
            if (price < b) {
                max = Math.max(max, now);
                b = price;
                s = b;
                now = 0;
            } else if (price > s) {
                s = price;
                now = s - b;
            }
        }
        return Math.max(max, now);
    }*/
//    public int maxProfit(int[] prices) {
//        int s = 0, b = 0, p = 0;
//        if (prices.length > 0) {
//            b = prices[0];
//        }
//        for (int price : prices) {
//            if (price < s) {
//                p += s - b;
//                b = price;
//                s = b;
//            } else {
//                s = price;
//            }
//        }
//        return p + s - b;
//    }

    //123 TODO
    public int maxProfit(int[] prices) {
        int b1 = -1, s1 = -1, b2 = -1, s2 = -1, b3 = -1, s3 = -1, m1, m2, m3, max;
//        int b = Integer.MAX_VALUE, s = b, now, max = 0, pre = 0;
        for (int price : prices) {
            if (b1 < 0) {
                b1 = s1 = price;
            } else {
                if (b2 < 0) {
                    b2 = s2 = price;
                } else {
                    if (b3 < 0) {
                        b3 = s3 = price;
                    } else {
                        if (price >= s3) s3 = price;
                        else {
                            m1 = s1 - b1 + Math.max(s2 - b2, Math.max(s3 - b3, s3 - b2));
                            m2 = s2 - b1 + s3 - b3;
                            m3 = s2 - b2 + s3 - b3;
                            max = Math.max(m1, Math.max(m2, m3));
                            if (max == m2) {
                                s1 = s2;
                                b2 = b3;
                                s2 = s3;
                                b3 = s3 = price;
                            } else if (max == m3) {
                                b1 = b2;
                                s1 = s2;
                                b2 = b3;
                                s2 = s3;
                                b3 = s3 = price;
                            } else {
                                if (m1 + b1 - s1 == s2 - b2) {
                                    b3 = s3 = price;
                                } else if (m1 + b1 - s1 == s3 - b2) {
                                    s2 = s3;
                                    b3 = s3 = price;
                                } else {
                                    b2 = b3;
                                    s2 = s3;
                                    b3 = s3 = price;
                                }
                            }
                        }
                    }
                }
            }
        }
        m1 = s1 - b1 + Math.max(s2 - b2, Math.max(s3 - b3, s3 - b2));
        m2 = s2 - b1 + s3 - b3;
        m3 = s2 - b2 + s3 - b3;
        max = Math.max(s3-s1,Math.max(m1, Math.max(m2, m3)));
        System.out.println(b1 + " " + s1 + " " + b2 + " " + s2 + " " + b3 + " " + s3);
        return max;
    }
}
