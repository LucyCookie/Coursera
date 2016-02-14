package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiqu on 1/7/16.
 * TODO
 */
public class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        Interval merge, first, last, cur;
        List<Interval> merged = new ArrayList<Interval>();
        int size = intervals.size(), i, low = newInterval.start, high = newInterval.end, mstart = size, mend = -1;
        for (i = 0; i < size; i++) {
            cur = intervals.get(i);
            if (cur.start <= high) {
                mend = i;
            } else {
                break;
            }
            if (cur.end < low) {
                mstart = i + 1;
                mend=i+1;
            }else {
                if (cur.start>high) {
                    break;
                }else {

                }

            }
        }
        return merged;
    }
}
