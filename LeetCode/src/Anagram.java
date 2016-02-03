import java.util.Arrays;

/**
 * Created by qiqu on 1/20/16.
 */
public class Anagram {
    public boolean isAnagram(String s, String t) {
        int size;
        if ((size = s.length()) != t.length()) return false;
        char[] sChar = s.toCharArray(), tChar = t.toCharArray();
        Arrays.sort(sChar);
        Arrays.sort(tChar);
        for (int i = 0; i < size; i++) {
            if (sChar[i] != tChar[i]) return false;
        }
        return true;
    }
}
