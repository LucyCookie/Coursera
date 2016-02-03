import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiqu on 1/19/16.
 */
public class MinEdit {
    public int minDistance(String word1, String word2) {
        int s1 = word1.length(), s2 = word2.length(), i, j, pre, tmp;
        char c;
        int[] distance = new int[s1 + 1];
        for (i = 0; i <= s1; i++) {
            distance[i] = i;
        }
        for (j = 0; j < s2; j++) {
            pre = distance[0];
            c = word2.charAt(j);
            distance[0] = j + 1;
            for (i = 0; i < s1; i++) {
                tmp = distance[i + 1];
                if (word1.charAt(i) == c) distance[i + 1] = pre;
                else {
                    distance[i + 1] = Math.min(pre + 1, Math.min(distance[i] + 1, distance[i + 1] + 1));
                }
                pre = tmp;
            }
        }
        return distance[s1];
    }
}
