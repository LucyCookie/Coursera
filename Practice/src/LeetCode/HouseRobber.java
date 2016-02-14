package LeetCode;

/**
 * Created by qiqu on 1/7/16.
 * Leetcode 198
 */
public class HouseRobber {
    public int rob(int[] nums) {
        int now=0, last=0, tmp=0;
        for (int i:nums){
            tmp=now;
            now=Math.max(now, last+i);//when a new house come, choose to rob or not; if rob, use last; else use now. last could be same with now.
            last=tmp;
        }
        return now;
    }
}
