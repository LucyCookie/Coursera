/**
 * 58 easy
 * Created by Cookie on 28/7/2015.
 */
public class LastWord {
    public static String getLastWord(String sentence) {
        String[] words = sentence.split(" ");
        if(words.length>0) {
            return words[words.length - 1];
        } else return "";
    }
}
