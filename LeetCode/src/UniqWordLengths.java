import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by qiqu on 1/5/16.
 * Leetcode 318
 */
//TODO: have to use bit to record the existence of a letter.
public class UniqWordLengths {
    public int maxProduct(String[] words) {
        int maxProduct = 0, num = words.length, oneL, twoL;
        String cur, test;
        Arrays.sort(words, new Comparator<String>() {
            public int compare(String a, String b) {
                return b.length() - a.length();
            }
        });
        int[] uniqLetter=new int[num];
        for (int i=0;i<num;i++){
            cur=words[i];
            for (int j=0;j<cur.length();j++){
                uniqLetter[i]|=1<<(cur.charAt(j)-'a');
            }
        }
        for (int i = 0; i < num - 1; i++) {
            cur = words[i];
            oneL = cur.length();
            if (oneL * oneL <= maxProduct) break;
            for (int j = i + 1; j < num; j++) {
                test = words[j];
                twoL = test.length();
                if ((uniqLetter[i] & uniqLetter[j]) == 0) {
                    maxProduct = Math.max(maxProduct, oneL * twoL);
                    break;
                }
            }
        }
        return maxProduct;
    }
}
