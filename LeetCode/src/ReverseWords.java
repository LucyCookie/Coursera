import java.util.Stack;

/**
 * Created by qiqu on 1/21/16.
 */
public class ReverseWords {
    public String reverseWords(String s) {
        String word = "", reversed = "";
        String[] words = s.trim().split("\\s++");
        int n = words.length;
        for (int i = 1; i < n; i++) {
            reversed = words[i] + " " + reversed;
        }
        if (n > 0) reversed += words[0];
        return reversed;
    }
}
